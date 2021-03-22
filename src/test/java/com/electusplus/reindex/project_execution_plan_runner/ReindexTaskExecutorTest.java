//package com.bidalab.reindexer.runner;
//
//import com.bidalab.reindexer.elasticsearch.ElasticsearchDbProvider;
//import com.bidalab.reindexer.pojo.ProjectPOJO;
//import com.bidalab.reindexer.reindex_exectution_plan_builder.reindex.ReindexTaskPOJO;
//import com.bidalab.reindexer.rest.GeneralRESTSettings;
//import com.bidalab.reindexer.utils.GeneralUtils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.reindex.ReindexRequest;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class ReindexTaskExecutorTest  extends GeneralRESTSettings {
//
//    @Test
//    public void run() throws JsonProcessingException, InterruptedException {
////        final String SOURCE_PROJECT_FILE = "rest/generate_plan_with_sufix_and_prefix.json";
//        final String SOURCE_INDEX = ".monitoring-es-7-2020.09.20";
//        final String DESTINATION_INDEX = "test_mon";
//        saveProject();
//
//        ElasticsearchDbProvider elasticsearchDbProvider = new ElasticsearchDbProvider();
//        RestHighLevelClient client = elasticsearchDbProvider.initHighLevelClient("localhost", "http", 9200);
//        ReindexRequest reindexRequest = new ReindexRequest();
////        reindexRequest.setSourceIndices(SOURCE_INDEX);
//        reindexRequest.setDestIndex(DESTINATION_INDEX);
//        ReindexTaskPOJO reindexTask = new ReindexTaskPOJO(SOURCE_INDEX,reindexRequest);
//        ReindexTaskExecutor taskExecutor = new ReindexTaskExecutor("1", reindexTask, client);
//        taskExecutor.run();
//        Thread.sleep(100000000);
//        System.out.println("aaa");
//    }
//
//
//
////        try {
////        assertTrue("Save step failed", saveProject(newProject));
////        ProjectPOJO savedProject = getProjectById(newProject.getProjectId());
////        assertEquals("Saved project not equals to expected", newProject.toString(), savedProject.toString());
////        assertFalse("Error in project delete", getValidateProjectsName(newProject.getProjectName()));
////        assertTrue("Error in the validate endpoint", deleteProject(newProject.getProjectId()));
////        System.out.println("Save endpoint test PASSED!");
////        System.out.println("Validate endpoint test PASSED!");
////        System.out.println("Delete endpoint test PASSED!");
////        System.out.println("Get dy ID endpoint test PASSED!");
////        System.out.println("\n");
////    } catch (RuntimeException e) {
////        fail(e.getMessage());
////        System.out.println("\n");
////    }
////}
//
//    private boolean saveProject() throws JsonProcessingException {
//        ProjectPOJO newProject = new ProjectPOJO();
//        newProject.setProjectId("1");
//        newProject.setProjectName("Test");
//        String response = httpClient.postRequest("/reindexer/reindex_settings/save", objectToString(newProject));
//        return Boolean.parseBoolean(response);
//    }
//}