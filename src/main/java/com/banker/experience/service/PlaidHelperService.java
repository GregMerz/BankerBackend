package com.banker.experience.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.banker.experience.dao.UserRepo;
import com.banker.experience.data.User;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaidHelperService {

    @Autowired
    UserRepo userRepo;

    @Value("${plaid.clientId}")
    private String plaidClientId;

    @Value("${plaid.secret}")
    private String plaidSecret;

    @Value("${plaid.products}")
    private String[] plaidProducts;

    @Value("${plaid.country_codes}")
    private String[] countryCodes;

    public String getInfo() {
        JSONObject obj = new JSONObject();
        obj.put("item_id", JSONObject.NULL);
        obj.put("access_token", JSONObject.NULL);
        obj.put("products", plaidProducts);

        return obj.toString();
    }

    public String createLinkToken() {
        JSONObject body = new JSONObject();

        JSONObject user = new JSONObject();
        user.put("client_user_id", "user-id");

        body.put("client_id", plaidClientId);
        body.put("secret", plaidSecret);
        body.put("user", user);
        body.put("client_name", "Plaid Quickstart");
        body.put("products", plaidProducts);
        body.put("country_codes", countryCodes);
        body.put("language", "en");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.plaid.com/link/token/create"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(body.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
                return response.body();
            } else {
                System.out.println("Returned with response code: " + response.statusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String createAccessToken(String publicToken, int id) {
        JSONObject body = new JSONObject();

        body.put("client_id", plaidClientId);
        body.put("secret", plaidSecret);
        body.put("public_token", publicToken);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.plaid.com/item/public_token/exchange"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(body.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                JSONObject json = new JSONObject(responseBody);
                String accessToken = json.getString("access_token");
                String itemId = json.getString("item_id");

                // TODO: Need to check if user with id exists
                User userInDB = userRepo.findById(id).get();
                userInDB.setAccessToken(accessToken);

                userRepo.save(userInDB);

                JSONObject ans = new JSONObject();
                ans.put("access_token", accessToken);
                ans.put("item_id", itemId);
                ans.put("error", JSONObject.NULL);

                System.out.println(ans.toString());

                return ans.toString();
            } else {
                System.out.println("Returned with response code: " + response.statusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String fetchTransactions(int count, String accessToken) {
        JSONObject body = new JSONObject();

        body.put("client_id", plaidClientId);
        body.put("secret", plaidSecret);
        body.put("access_token", accessToken);
        body.put("cursor", JSONObject.NULL);
        body.put("count", 8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.plaid.com/transactions/sync"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(body.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                JSONObject json = new JSONObject(responseBody);
                JSONArray addedTxns = (JSONArray) json.get("added");

                System.out.println(addedTxns.toString());

                return addedTxns.toString();
            } else {
                System.out.println("Returned with response code: " + response.statusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
