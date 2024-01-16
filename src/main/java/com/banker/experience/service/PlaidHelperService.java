package com.banker.experience.service;

import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.banker.experience.utils.PresetHttpClient;

@Service
public class PlaidHelperService {

    @Value("${plaidProperties.clientId}")
    private String clientId;

    @Value("${plaidProperties.secret}")
    private String secretKey;

    @Value("${plaidProperties.env}")
    private String env;

    @Value("${plaidProperties.products}")
    private String[] products;

    @Value("${plaidProperties.countryCodes}")
    private String[] countryCodes;

    @Value("${plaidProperties.redirectURI}")
    private String redirectURI;

    public String getInfo() {
        JSONObject info = new JSONObject()
                .put("item_id", JSONObject.NULL)
                .put("access_token", JSONObject.NULL)
                .put("products", products);

        return info.toString();
    }

    public String createLinkToken() {
        String uri = "https://sandbox.plaid.com/link/token/create";
        String[] headers = new String[] { "Content-Type", "application/json" };
        JSONObject configs = new JSONObject()
                .put("client_id", clientId)
                .put("secret", secretKey)
                .put("user", new JSONObject()
                        .put("client_user_id", "user_id"))
                .put("client_name", "Plaid Quickstart")
                .put("products", products)
                .put("country_codes", countryCodes)
                .put("language", "en");

        if (redirectURI != "") {
            configs.put("redirect_uri", redirectURI);
        }

        // if (PLAID_ANDROID_PACKAGE_NAME !== '') {
        // configs.android_package_name = PLAID_ANDROID_PACKAGE_NAME
        // }

        HttpResponse<String> response = new PresetHttpClient().httpPost(uri, headers, configs);

        if (response.statusCode() != 200) {
            return null;
        }

        return response.body();
    }

    public String setAccessToken(String accessToken) {
        String uri = "https://sandbox.plaid.com/item/public_token/exchange";
        String[] headers = new String[] { "Content-Type", "application/json" };
        JSONObject data = new JSONObject()
                .put("client_id", clientId)
                .put("secret", secretKey)
                .put("public_token", accessToken);

        HttpResponse<String> response = new PresetHttpClient().httpPost(uri, headers, data);

        if (response.statusCode() != 200) {
            System.out.println(response.body());

            return null;
        }

        String tokenResponse = response.body();
        JSONObject tokenResponseJSON = new JSONObject(tokenResponse);

        JSONObject createJsonResponse = new JSONObject()
                .put("access_token", tokenResponseJSON.getString("access_token"))
                .put("item_id", "item_id")
                .put("error", JSONObject.NULL);

        return createJsonResponse.toString();
    }

    public String getTransactions() {
        return null;
    }
}
