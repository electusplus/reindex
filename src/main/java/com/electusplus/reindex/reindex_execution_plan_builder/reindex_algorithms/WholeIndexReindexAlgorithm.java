package com.electusplus.reindex.reindex_execution_plan_builder.reindex_algorithms;

import com.electusplus.reindex.project_settings.ESHostPOJO;
import com.electusplus.reindex.project_settings.EsSettings;
import com.electusplus.reindex.reindex_execution_plan_builder.reindex_plan.ReindexTaskPOJO;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.index.reindex.RemoteInfo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WholeIndexReindexAlgorithm extends ReindexAlgorithmPOJO implements IReindexAlgorithm {

    public WholeIndexReindexAlgorithm(final boolean isNew) {
        super("Whole index",
               new LinkedList<>(),
                false
                );
    }

    public WholeIndexReindexAlgorithm(ReindexAlgorithmPOJO reindexAlgorithm) {
        super(reindexAlgorithm);
    }

    public WholeIndexReindexAlgorithm() {
    }

    @Override
    public ReindexTaskPOJO generateRequestFromReprocessing(EsSettings source, String index, boolean isRemote, Map<String, Object> reprocessingParams) {
        ReindexRequest request = new ReindexRequest();
//        request.setSourceQuery(QueryBuilders.matchAllQuery());
        ReindexTaskPOJO requestTask = new ReindexTaskPOJO(index, request);
        requestTask.setReindexParams("Whole index: " + index);
        return requestTask;
    }
    private ReindexRequest buildRemoteRequest(final EsSettings source) {
        ReindexRequest request = new ReindexRequest();
        ESHostPOJO esHost = new ESHostPOJO(source);

        request.setRemoteInfo(
                new RemoteInfo(
                        esHost.getProtocol(),
                        esHost.getDomain(),
                        esHost.getPort(),
                        null,
                        new BytesArray(QueryBuilders.matchAllQuery().toString()),
                        source.getUsername(), source.getPassword(), Collections.emptyMap(),
                        new TimeValue(100, TimeUnit.MILLISECONDS),
                        new TimeValue(100, TimeUnit.SECONDS)
                ));
        return request;
    }
    @Override
    public List<ReindexTaskPOJO> generateRequests(final EsSettings source, final String index, final boolean isRemote, final String projectId) {
        List<ReindexTaskPOJO> requestList = new LinkedList<>();
        ReindexRequest request;
        if (isRemote) {
            request = buildRemoteRequest(source);
        } else {
            request = new ReindexRequest();
        }
//        request.setSourceQuery(QueryBuilders.matchAllQuery());
        ReindexTaskPOJO requestTask = new ReindexTaskPOJO(index, request);
        requestTask.setReindexParams("Whole index: " + index);
        requestList.add(requestTask);
        return requestList;
    }

    @Override
    public String toString() {
        return "TimeSeriesReindexAlgorithm{" +
                "reindexAlgorithmName='" + reindexAlgorithmName + '\'' +
                ", algorithmParams=" + algorithmParams +
                ", isChecked=" + isSelected +
                '}';
    }
}
