//package com.bidalab.reindexer.reindex_exectution_plan_builder.reindex;
//
//import com.bidalab.reindexer.pojo.ProjectPOJO;
//import com.bidalab.reindexer.reindex_exectution_plan_builder.reindex_plan.ReindexPlanBuilder;
//import com.bidalab.reindexer.utils.GeneralUtils;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class ReindexPlanBuilderTest {
//    final ObjectMapper mapper = new ObjectMapper();
//    final GeneralUtils utils = new GeneralUtils();
// generate_plan_with_sufix_and_prefix_for_multiindex.json
//    @Test
//    public void generatePlanTestWithIndexPrefixAndSufix() throws JsonProcessingException {
//        final String SOURCE_PROJECT_FILE = "rest/generate_plan_with_sufix_and_prefix.json";
//        final String EXPECTED_PROJECT_FILE = "rest/expected_generate_plan_with_sufix_and_prefix.json";
//        generatePlanAndTestWithExpected(SOURCE_PROJECT_FILE, EXPECTED_PROJECT_FILE);
//        System.out.println("Generate plan with prefix and suffix PASSED!");
//    }
//
//    @Test
//    public void generatePlanTestWithMergeToOneIndex() throws JsonProcessingException {
//        final String SOURCE_PROJECT_FILE = "rest/generate_plan_with_merge_to_one_index.json";
//        final String EXPECTED_PROJECT_FILE = "rest/expected_generate_plan_with_merge_to_one_index.json";
//        generatePlanAndTestWithExpected(SOURCE_PROJECT_FILE, EXPECTED_PROJECT_FILE);
//        System.out.println("Generate plan with merge to one index PASSED!");
//    }
//    @Test
//    public void generatePlanTestWithPipeline() throws JsonProcessingException {
//        final String SOURCE_PROJECT_FILE = "rest/generate_plan_with_pipeline.json";
//        final String EXPECTED_PROJECT_FILE = "rest/expected_generate_plan_with_pipeline.json";
//        generatePlanAndTestWithExpected(SOURCE_PROJECT_FILE, EXPECTED_PROJECT_FILE);
//        System.out.println("Generate plan with sending to pipeline PASSED!");
//    }
//
//    private void generatePlanAndTestWithExpected(final String SOURCE_PROJECT_FILE, final String EXPECTED_PROJECT_FILE) throws JsonProcessingException {
//        ProjectPOJO sourceProject = mapper.readValue(utils.readFileFromResourcesToString(SOURCE_PROJECT_FILE), ProjectPOJO.class);
//        ReindexPlanBuilder expectedPlan = mapper.readValue(utils.readFileFromResourcesToString(EXPECTED_PROJECT_FILE), ReindexPlanBuilder.class);
//        ReindexPlanBuilder plan = new ReindexPlanBuilder(sourceProject);
//        plan.generatePlan();
//        plan.getReindexJobsStatus().forEach((index, job) -> {
//            job.getReindexTasks().forEach(x -> x.setReindexRequest(null));
//        });
//        expectedPlan.getReindexJobsStatus().forEach((index, job) -> {
//            job.getReindexTasks().forEach(x -> x.setReindexRequest(null));
//        });
//        System.out.println(objectToString(plan));
//        assertEquals("The plan and the expected plan not equals!", plan.toString(), expectedPlan.toString());
//    }
//
//    //        System.out.println(objectToString(plan));
//    private String objectToString(Object object) throws JsonProcessingException {
//        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
//        return mapper.writeValueAsString(object);
//    }
//}