package com.banker.experience.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PresetHttpClient {

    public HttpResponse<String> httpGet(String uri, String[] headers) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .headers(headers)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public HttpResponse<String> httpPost(String uri, String[] headers, JSONObject data) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .headers(headers)
                    .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    // public String httpDelete(String uri, String headers, JSONObject data) {
    // try {
    // HttpRequest request = HttpRequest.newBuilder()
    // .uri(new URI(uri))
    // .headers(headers)
    // .DELETE(HttpRequest.BodyPublishers.ofString(data.toString()))
    // .build();
    // HttpResponse<String> response = HttpClient.newHttpClient()
    // .send(request, HttpResponse.BodyHandlers.ofString());

    // if (response.statusCode() == 200) {
    // return response.body();
    // }
    // } catch (URISyntaxException e) {
    // e.printStackTrace();
    // }

    // return null;
    // }
}
