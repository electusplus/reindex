package com.electusplus.reindex.reindex_execution_plan_builder.dao.template;

import com.electusplus.reindex.reindex_execution_plan_monitoring.BasicStatusPOJO;
import org.elasticsearch.client.indices.CreateIndexRequest;

public class TemplateTaskPOJO extends BasicStatusPOJO {

    private CreateIndexRequest createIndexRequest;

    public TemplateTaskPOJO(String sourceIndexName, CreateIndexRequest createIndexRequest) {
        this.createIndexRequest = createIndexRequest;
    }

    public CreateIndexRequest getCreateIndexRequest() {
        return createIndexRequest;
    }

    public void setCreateIndexRequest(CreateIndexRequest createIndexRequest) {
        this.createIndexRequest = createIndexRequest;
    }
}
