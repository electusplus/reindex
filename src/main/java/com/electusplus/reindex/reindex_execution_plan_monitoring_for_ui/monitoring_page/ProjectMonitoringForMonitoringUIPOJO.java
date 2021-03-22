//package com.bidalab.reindexer.reindex_execution_plan_monitoring_for_ui.monitoring_page;
//
//import com.bidalab.reindexer.constants.EProjectStatus;
//import com.bidalab.reindexer.reindex_execution_plan_monitoring.ProjectStatusPOJO;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ProjectMonitoringForMonitoringUIPOJO {
//    @JsonProperty("project_id")
//    private String projectId;
//    @JsonProperty("project_name")
//    private String projectName;
//    private EProjectStatus status;
//    @JsonProperty("execution_progress")
//    private int executionProgress;
//    @JsonProperty("total_tasks")
//    private int totalTasks;
//    @JsonProperty("waiting_tasks")
//    private int waitingTasks;
//    @JsonProperty("succeeded_tasks")
//    private int succeededTasks;
//    @JsonProperty("failed_tasks")
//    private int failedTasks;
//    @JsonProperty("reindex_jobs_status")
//    private List<ReindexTaskStatusForUIMonitoringPagePOJO> reindexTasksStatus = new LinkedList<>();
//    @JsonProperty("cluster_jobs_status")
//    private List<ClusterTaskStatusForUIMonitoringPagePOJO> clusterTasksStatus = new LinkedList<>();
//
//    public ProjectMonitoringForMonitoringUIPOJO(final ProjectStatusPOJO projectStatus, int limitForUITable) {
//        this.projectId = projectStatus.getProjectId();
//        this.projectName = projectStatus.getProjectName();
//        this.executionProgress = projectStatus.getExecutionProgress();
//        this.totalTasks = projectStatus.getTasksNumber();
//        this.waitingTasks = projectStatus.getTasksNumber() - projectStatus.getSucceededTasks() - projectStatus.getFailedTasks();
//        this.succeededTasks = projectStatus.getSucceededTasks();
//        this.failedTasks = projectStatus.getFailedTasks();
//        this.status = projectStatus.getStatus();
//        List<ReindexTaskStatusForUIMonitoringPagePOJO> allTasks = projectStatus.generateTaskStatusListForUIMonitoringPage();
//        reindexTasksStatus.addAll(allTasks.stream()
//                .filter(ReindexTaskStatusForUIMonitoringPagePOJO::isSucceeded)
//                .limit(limitForUITable)
//                .collect(Collectors.toList()));
//        reindexTasksStatus.addAll(allTasks.stream()
//                .filter(ReindexTaskStatusForUIMonitoringPagePOJO::isFailed)
//                .limit(limitForUITable)
//                .collect(Collectors.toList()));
//        reindexTasksStatus.addAll(allTasks.stream()
//                .filter(ReindexTaskStatusForUIMonitoringPagePOJO::isInActiveProcess)
//                .limit(limitForUITable)
//                .collect(Collectors.toList()));
//        reindexTasksStatus.addAll(allTasks.stream()
//                .filter(task -> !task.isInActiveProcess() && !task.isDone())
//                .limit(limitForUITable)
//                .collect(Collectors.toList()));
//        clusterTasksStatus.addAll(projectStatus.getClusterTasksStatus().stream()
//                .map(ClusterTaskStatusForUIMonitoringPagePOJO::new)
//                .collect(Collectors.toList()));
//    }
//
//    public EProjectStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(EProjectStatus status) {
//        this.status = status;
//    }
//
//    public int getExecutionProgress() {
//        return executionProgress;
//    }
//
//    public void setExecutionProgress(int executionProgress) {
//        this.executionProgress = executionProgress;
//    }
//
//    public int getTotalTasks() {
//        return totalTasks;
//    }
//
//    public void setTotalTasks(int totalTasks) {
//        this.totalTasks = totalTasks;
//    }
//
//    public int getWaitingTasks() {
//        return waitingTasks;
//    }
//
//    public void setWaitingTasks(int waitingTasks) {
//        this.waitingTasks = waitingTasks;
//    }
//
//    public int getSucceededTasks() {
//        return succeededTasks;
//    }
//
//    public void setSucceededTasks(int succeededTasks) {
//        this.succeededTasks = succeededTasks;
//    }
//
//    public int getFailedTasks() {
//        return failedTasks;
//    }
//
//    public void setFailedTasks(int failedTasks) {
//        this.failedTasks = failedTasks;
//    }
//
//    public String getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(String projectId) {
//        this.projectId = projectId;
//    }
//
//    public String getProjectName() {
//        return projectName;
//    }
//
//    public void setProjectName(String projectName) {
//        this.projectName = projectName;
//    }
//
//    public List<ReindexTaskStatusForUIMonitoringPagePOJO> getReindexTasksStatus() {
//        return reindexTasksStatus;
//    }
//
//    public void setReindexTasksStatus(List<ReindexTaskStatusForUIMonitoringPagePOJO> reindexTasksStatus) {
//        this.reindexTasksStatus = reindexTasksStatus;
//    }
//}
