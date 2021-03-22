package com.electusplus.reindex.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpClient {
    private final String SERVER_HOST;
    Client client;

    public HttpClient(String serverHost) {
        this.SERVER_HOST = serverHost;
    }

    public void closeClient() {
        client.destroy();
    }

    public String getRequest(final String uri) {
        try {
            client = Client.create();
            WebResource webResource = client.resource(SERVER_HOST + uri);
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            return output;
        } catch (Exception e) {
//            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String postRequest(final String uri, final String body) {
        try {
            client = Client.create();
            WebResource webResource = client.resource(SERVER_HOST + uri);

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, body);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            return  output;

        } catch (Exception e) {
//            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String deleteRequest(final String uri) {
        try {
            client = Client.create();
            WebResource webResource = client.resource(SERVER_HOST + uri);
            ClientResponse response = webResource.accept("application/json")
                    .delete(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            return response.getEntity(String.class);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}