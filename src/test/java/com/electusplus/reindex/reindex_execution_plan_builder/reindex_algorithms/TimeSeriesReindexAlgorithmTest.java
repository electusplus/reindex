//package com.bidalab.reindexer.reindex_execution_plan_builder.reindex_algorithms;
//
//import com.bidalab.reindexer.project_settings.EsSettings;
//import com.bidalab.reindexer.reindex_execution_plan_builder.reindex_plan.ReindexTaskPOJO;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertTrue;
//
//public class TimeSeriesReindexAlgorithmTest {
//    TimeSeriesReindexAlgorithm timeSeriesReindexAlgorithm = new TimeSeriesReindexAlgorithm(true);
//
//    @Test
//    public void generateRequests() {
//        final String REQUIRED_INDEX = ".monitoring-es-7-*";
//        EsSettings esSettings = new EsSettings();
//        esSettings.setEs_host("http://localhost:9200");
//        List<ReindexTaskPOJO> requests = timeSeriesReindexAlgorithm.generateRequests(esSettings, REQUIRED_INDEX, false);
//        assertTrue("TimeSeriesReindexAlgorithmTest FAILED!", requests.size() > 0);
//        System.out.println("TimeSeriesReindexAlgorithmTest PASSED");
//    }
//
//
//}