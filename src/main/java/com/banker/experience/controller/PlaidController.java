package com.banker.experience.controller;

import com.banker.experience.service.PlaidHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plaid/")
@CrossOrigin(origins = "http://localhost:19006")
public class PlaidController {
    
    @Autowired
    private PlaidHelperService plaidHelperService;

    @PostMapping(path = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInfo() {
        return plaidHelperService.getInfo();
    }

    @PostMapping(path = "create_link_token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLinkToken() {
        return plaidHelperService.createLinkToken();
    }

    @PostMapping(path = "set_access_token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String setAccessToken(@RequestParam("public_token") String publicToken) {
        return plaidHelperService.createAccessToken(publicToken);
    }

    @PostMapping(path = "transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String fetchTransactions() {
        return plaidHelperService.fetchTransactions(8, "access-sandbox-221d8ddd-cf10-4661-8525-276c53cf0ce1");
    }
}
