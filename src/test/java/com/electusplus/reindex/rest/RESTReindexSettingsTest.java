//package com.bidalab.reindexer.rest;
//
//import com.bidalab.reindexer.pojo.ProjectPOJO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class RESTReindexSettingsTest extends GeneralRESTSettings {
//
//    @Test
//    public void testNewEndpoint() throws Exception {
//        final String RESPONSE_FILE = "rest/new_project.json";
//        ProjectPOJO expectedProject = mapper.readValue(utils.readFileFromResourcesToString(RESPONSE_FILE), ProjectPOJO.class);
//        expectedProject.setProjectId("");
//        ProjectPOJO project = new ProjectPOJO();
//        try {
//            project = getNewProject();
//            project.setProjectId("");
//            assertEquals(expectedProject.toString(), project.toString());
//            System.out.println("New endpoint test PASSED!");
//            System.out.println("\n");
//        } catch (RuntimeException e) {
//            System.out.println("New endpoint test FAILED!");
//            System.out.println("Expected response: " + expectedProject.toString());
//            System.out.println("Actual Response: " + project.toString());
//            System.out.println("\n");
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSaveEndpoint() throws Exception {
//        ProjectPOJO newProject = getNewProject();
//        newProject.setProjectName("Test");
//        try {
//            assertTrue("Save step failed", saveProject(newProject));
//            ProjectPOJO savedProject = getProjectById(newProject.getProjectId());
//            assertEquals("Saved project not equals to expected", newProject.toString(), savedProject.toString());
//            assertFalse("Error in project delete", getValidateProjectsName(newProject.getProjectName()));
//            assertTrue("Error in the validate endpoint", deleteProject(newProject.getProjectId()));
//            System.out.println("Save endpoint test PASSED!");
//            System.out.println("Validate endpoint test PASSED!");
//            System.out.println("Delete endpoint test PASSED!");
//            System.out.println("Get dy ID endpoint test PASSED!");
//            System.out.println("\n");
//        } catch (RuntimeException e) {
//            fail(e.getMessage());
//            System.out.println("\n");
//        }
//    }
//
//    private boolean saveProject(ProjectPOJO newProject) throws JsonProcessingException {
//        String response = httpClient.postRequest("/reindexer/reindex_settings/save", objectToString(newProject));
//        return Boolean.parseBoolean(response);
//    }
//
//    private boolean deleteProject(final String projectId) {
//        return Boolean.parseBoolean(httpClient.deleteRequest("/reindexer/reindex_settings/delete/" + projectId));
//    }
//
//    private ProjectPOJO getProjectById(final String projectId) throws com.fasterxml.jackson.core.JsonProcessingException {
//        String response = httpClient.getRequest("/reindexer/reindex_settings/get/" + projectId);
//        return mapper.readValue(response, ProjectPOJO.class);
//    }
//    private boolean getValidateProjectsName(final String projectName) {
//        String response = httpClient.getRequest("/reindexer/validate/" + projectName);
//        return Boolean.parseBoolean(response);
//    }
//
//    private ProjectPOJO getNewProject() throws com.fasterxml.jackson.core.JsonProcessingException {
//        String response = httpClient.getRequest("/reindexer/reindex_settings/new");
//        return mapper.readValue(response, ProjectPOJO.class);
//    }
//
//}