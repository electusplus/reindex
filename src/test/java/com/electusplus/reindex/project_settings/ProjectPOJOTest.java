//package com.bidalab.reindexer.pojo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//
//import static org.junit.Assert.*;
//
//public class ProjectPOJOTest {
//    ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void parseJsonFileToExistingProjectTest() throws IOException {
//        final String SAMPLE_PROJECT_FILE = "E:\\BiDaCSo\\app\\BiDaCSo-toolkit\\reindexer\\app\\src\\main\\resources\\mock\\project.json";
//        ProjectPOJO project;
//        try {
//            project = mapper.readValue(new File(SAMPLE_PROJECT_FILE), ProjectPOJO.class);
//            System.out.println(project.toString());
//        } catch (UnrecognizedPropertyException e) {
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void parseJsonFileToNewProjectTest() throws IOException {
//        final String SAMPLE_PROJECT_FILE = "E:\\BiDaCSo\\app\\BiDaCSo-toolkit\\reindexer\\app\\src\\main\\resources\\mock\\new_project.json";
//        ProjectPOJO project;
//        try {
//            project = mapper.readValue(new File(SAMPLE_PROJECT_FILE), ProjectPOJO.class);
//            System.out.println(project.toString());
//        } catch (UnrecognizedPropertyException e) {
//            fail(e.getMessage());
//        }
//    }
//}