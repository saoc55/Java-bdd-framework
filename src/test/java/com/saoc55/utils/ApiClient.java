package com.saoc55.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public static final String BASE_URI = "https://parabank.parasoft.com";
    public static final String BASE_PATH = "/parabank/services/bank";
    public static final String VALID_USERNAME = "john";
    public static final String VALID_PASSWORD = "demo";
    public static final String INVALID_USERNAME ="notauser";
    public static final String INVALID_PASSWORD = "wrongpass";

    public static RequestSpecification baseRequest(){
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");
    }

    public static Response login(String username, String password){
        return baseRequest()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/login/" + username + "/" + password);
    }

    public static Response getAccounts(int customerId){
        return baseRequest()
                .get("/customers/" + customerId + "/accounts");
    }

    public static Response getAccount(int accountId){
        return baseRequest()
                .get("/accounts/" + accountId);
    }

    public static Response transfer(int fromAccount, int toAccount, int amount){
        return baseRequest()
                .queryParam("fromAccountId", fromAccount)
                .queryParam("toAccountId", toAccount)
                .queryParam("amount", amount)
                .post("/transfer");
    }


}
