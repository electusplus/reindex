//package com.bidalab.reindexer.reindex_execution_plan_builder.dao.template;
//
//import com.bidalab.reindexer.elasticsearch.ElasticsearchDbProvider;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.GetIndexTemplatesRequest;
//import org.elasticsearch.client.indices.GetIndexTemplatesResponse;
//import org.junit.Test;
//
//import java.io.IOException;
//
//import static org.junit.Assert.*;
//
//public class GetTemplateRequestControllerTest {
//    GetTemplateRequestController getTemplateRequestController = new GetTemplateRequestController();
//
//    @Test
//    public void generateGetTemplateRequestTest() throws IOException {
//        final String REQUIRED_TEMPLATE = ".kibana-event-log*";
//        final String EXPECTED_TEMPLATE = ".kibana-event-log";
//        ElasticsearchDbProvider elasticsearchDbProvider = new ElasticsearchDbProvider();
//        RestHighLevelClient client = elasticsearchDbProvider.initHighLevelClient("localhost", "http", 9200);
//        GetIndexTemplatesRequest request = getTemplateRequestController.generateGetTemplateRequest(REQUIRED_TEMPLATE);
//        GetIndexTemplatesResponse getTemplatesResponse = client.indices().getIndexTemplate(request, RequestOptions.DEFAULT);
//        if (getTemplatesResponse.getIndexTemplates().size() > 0) {
//            assertTrue("There is no templates with " + REQUIRED_TEMPLATE, (getTemplatesResponse.getIndexTemplates().get(0).name()).contains(EXPECTED_TEMPLATE));
//        }
//        else {
//            fail("There is no templates with " + REQUIRED_TEMPLATE);
//        }
//    }
//}