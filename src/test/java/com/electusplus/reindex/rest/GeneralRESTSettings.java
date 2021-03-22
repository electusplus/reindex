package com.electusplus.reindex.rest;

import com.electusplus.reindex.utils.GeneralUtils;
import com.electusplus.reindex.utils.HttpClient;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class GeneralRESTSettings {

    final static String SERVER_HOST = "localhost";
    final static int SERVER_PORT = 1234;
    final static String PROTOCOL = "http";

    public final ObjectMapper mapper = new ObjectMapper();
    public final GeneralUtils utils = new GeneralUtils();
    public static HttpClient httpClient;


    @BeforeClass
    public static void setUp() throws Exception {
//        final String SERVER_URL = PROTOCOL + "://" + SERVER_HOST + ":" + SERVER_PORT;
//        httpClient = new HttpClient(SERVER_URL);
//        Reindex.test(SERVER_HOST, SERVER_PORT);
    }

    protected String objectToString(Object object) throws JsonProcessingException {
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
        return mapper.writeValueAsString(object);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        httpClient.closeClient();
    }
}
