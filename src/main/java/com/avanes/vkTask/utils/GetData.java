package com.avanes.vkTask.utils;

import com.avanes.vkTask.ApiError.exception.NotFoundException;
import com.avanes.vkTask.constants.CrudOperations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public enum GetData {
    INSTANCE;

    public StringBuilder getDataFromServer(String address, CrudOperations operation) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(operation.name());
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new NotFoundException("Ошибка запроса. HTTP error code: " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while (((output = br.readLine()) != null)) {
                builder.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder;
    }

    public <T> HttpResponse<String> addData (T obj , String address) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public <T> HttpResponse<String> patchData (T obj , String address) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteData (String address) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(address))
                .DELETE()
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
