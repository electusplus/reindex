package com.electusplus.reindex.project_execution_plan_runner;

import com.electusplus.reindex.constants.EProjectStatus;
import com.electusplus.reindex.data_warehouse.DataWarehouse;
import com.electusplus.reindex.elasticsearch.ElasticsearchController;
import com.electusplus.reindex.elasticsearch.ElasticsearchDbProvider;
import com.electusplus.reindex.exceptions.ClusterConnectionException;
import com.electusplus.reindex.reindex_execution_plan_builder.dao.IClusterTaskDAO;
import com.electusplus.reindex.reindex_execution_plan_builder.reindex_plan.ReindexPlanPOJO;
import com.electusplus.reindex.reindex_execution_plan_monitoring.ProjectStatusPOJO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

class ReindexProjectExecutor implements Runnable {
    private static final Logger logger = LogManager.getLogger();

    private final ReindexPlanPOJO plan;
    private final ThreadPoolExecutor indicesThreadPool;
    private final List<ReindexJobExecutor> reindexJobs;
    private final ProjectStatusPOJO projectStatus;
    private final DataWarehouse dataWarehouse = DataWarehouse.getInstance();
    private final RestHighLevelClient destinationClient;
    private final RestHighLevelClient sourceClient;

    ReindexProjectExecutor(final ReindexPlanPOJO plan, long taskRefreshInterval) throws ClusterConnectionException {
        this.plan = plan;
        indicesThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(plan.getNumberOfConcurrentProcessedIndices());
        ElasticsearchDbProvider elasticsearchDbProvider = new ElasticsearchDbProvider();
        destinationClient = elasticsearchDbProvider.getHighLevelClient(plan.getConnectionSettings().getDestination(), plan.getProjectId());
        sourceClient = elasticsearchDbProvider.getHighLevelClient(plan.getConnectionSettings().getDestination(), plan.getProjectId());
        this.reindexJobs = plan.getReindexJobs().values().stream()
                .map(reindexJobPOJO ->
                        new ReindexJobExecutor(plan.getProjectId(), reindexJobPOJO, destinationClient, plan.getThreadsPerIndex(), taskRefreshInterval)
                )
                .collect(Collectors.toList());
        projectStatus = dataWarehouse.getProjectsStatus().get(plan.getProjectId());
    }

    @Override
    public void run() {
        logger.info("Start to execute plan: " + plan.getProjectId());
        projectStatus.setInActiveProcess(true);
        projectStatus.setStartTime(System.currentTimeMillis());
        ElasticsearchController elasticsearchController = new ElasticsearchController();
        projectStatus.setTotalDocs(plan.getReindexJobs().keySet().stream()
                .mapToLong(index -> {
                    try {
                        long docsCount = elasticsearchController.getIndexDocsCount(plan.getConnectionSettings().getSource(), plan.getProjectId(), index);
                        projectStatus.getReindexJobsStatus().stream()
                                .filter(job -> job.getSourceIndex().equals(index))
                                .forEach(job -> job.setTotalDocs(docsCount));
                        return docsCount;
                    } catch (ClusterConnectionException e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .sum());
        if (!plan.isDone()) {
            //Cluster tasks
            boolean clusterTaskResult = true;
            int i = 0;
            while (clusterTaskResult && i < plan.getClusterTasks().size() && !projectStatus.isInterrupted()) {
                if (!plan.getClusterTasks().get(i).isDone()) {
                    IClusterTaskDAO taskForExecution = plan.getClusterTasks().get(i).getRequestDAO();
//
//                    if ( plan.getClusterTasks().get(i).getRequestDAO() instanceof IsReindexRemoteClusterDefined){
//                        plan.getClusterTasks().get(i).getRequestDAO().execute(
//                                destinationClient,
//                                projectStatus.getClusterTasksStatus().stream()
//                                        .filter(task -> task.getSelfGeneratedTaskId().equals(taskForExecution.getSelfGeneratedTaskId()))
//                                        .findFirst().orElse(null)
//                        );
//                        continue;
//                    }
                    clusterTaskResult = plan.getClusterTasks().get(i).getRequestDAO().execute(
                            destinationClient,
                            projectStatus.getClusterTasksStatus().stream()
                                    .filter(task -> task.getSelfGeneratedTaskId().equals(taskForExecution.getSelfGeneratedTaskId()))
                                    .findFirst().orElse(null)
                    );
                    dataWarehouse.writeStatusToFile(plan.getProjectId());
                }
                i++;
            }

            if (clusterTaskResult && !projectStatus.isInterrupted()) {
                //Reindex tasks
                CompletableFuture<?>[] futures = reindexJobs.stream()
                        .map(job -> CompletableFuture.runAsync(job, indicesThreadPool))
                        .toArray(CompletableFuture[]::new);
                CompletableFuture.allOf(futures).join();
                projectStatus.updateStatus();
            } else {
                projectStatus.setStatus(EProjectStatus.FAILED);
                projectStatus.setFailed(true);
                projectStatus.setDone(true);
            }

            indicesThreadPool.shutdown();
            projectStatus.setInActiveProcess(false);
            projectStatus.setEndTime(System.currentTimeMillis());
            dataWarehouse.writeStatusToFile(plan.getProjectId());
        }
    }

    void stop() {
        projectStatus.setInActiveProcess(false);
        projectStatus.setInterrupted(true);
        reindexJobs.stream()
                .filter(job -> job.getReindexJobStatus().isInActiveProcess())
                .forEach(ReindexJobExecutor::stop);
        indicesThreadPool.shutdownNow();
        plan.setEndTime(System.currentTimeMillis());
        projectStatus.setEndTime(System.currentTimeMillis());
        dataWarehouse.writeStatusToFile(plan.getProjectId());
        logger.warn("Stop exclusion plan: " + plan.getProjectId());
    }
}