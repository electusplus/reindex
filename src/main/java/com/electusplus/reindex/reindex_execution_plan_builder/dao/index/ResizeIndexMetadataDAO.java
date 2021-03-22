package com.electusplus.reindex.reindex_execution_plan_builder.dao.index;

import com.electusplus.reindex.reindex_execution_plan_builder.dao.AClusterTaskDAO;
import com.electusplus.reindex.reindex_execution_plan_builder.dao.IClusterTaskDAO;
import org.elasticsearch.action.admin.indices.shrink.ResizeRequest;
import org.elasticsearch.action.admin.indices.shrink.ResizeResponse;
import org.elasticsearch.action.admin.indices.shrink.ResizeType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;

public class ResizeIndexMetadataDAO extends AClusterTaskDAO<ResizeRequest> implements IClusterTaskDAO {
    private final ResizeRequest request;

    public ResizeIndexMetadataDAO(String requestType, String... params) {
        super(requestType, params);
        this.request = generateRequest();
    }

    /**
     * param[0] - source index
     * param[1] - destination index
     *
     * @return request
     */
    @Override
    protected ResizeRequest generateRequest() {
        ResizeRequest request = new ResizeRequest(params[1], params[0]);
        request.setResizeType(ResizeType.CLONE);
        return request;
    }

    @Override
    protected boolean executeRequest(ResizeRequest request, RestHighLevelClient client) {
        try {
            ResizeResponse resizeResponse = client.indices().clone(request, RequestOptions.DEFAULT);
            return resizeResponse.isAcknowledged();
        } catch (IOException e) {
            logger.error("Suppressed: " + Arrays.toString(e.getSuppressed()));
            return false;
        }
    }

    @Override
    protected ResizeRequest getRequest() {
        return this.request;
    }
}
