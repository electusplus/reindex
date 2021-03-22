package com.electusplus.reindex.reindex_execution_plan_builder.dao.template;

import org.elasticsearch.client.indices.PutIndexTemplateRequest;

public class PutTemplateRequestController {

    public PutIndexTemplateRequest generatePutTemplateRequest(final String templateName) {
        PutIndexTemplateRequest request = new PutIndexTemplateRequest("my-template");
//        boolean acknowledged = putTemplateResponse.isAcknowledged();
        return request;
    }
}
