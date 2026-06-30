package com.saoc55.steps;

import com.saoc55.utils.ApiClient;
import com.saoc55.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountSteps {

    private final ScenarioContext ctx;
    
    public AccountSteps(ScenarioContext ctx){
        this.ctx = ctx;
    }

    @Given("I am logged in as a valid user")
    public void iAmLoggedInAsAValidUser(){
        Response loginResponse = ApiClient.login(
                ApiClient.VALID_USERNAME,
                ApiClient.VALID_PASSWORD
        );
        assertEquals(200, loginResponse.getStatusCode());
        ctx.customerId = loginResponse.jsonPath().getInt("id");
    }

    @When("I request all accounts for the customer")
    public void iRequestAllAccountsForTheCustomer(){
        ctx.response = ApiClient.getAccounts(ctx.customerId);
    }

    @When("I request a single account by ID")
    public void iRequestASingleAccountByID(){
        Response accountResponse = ApiClient.getAccounts(ctx.customerId);
        ctx.accountId = accountResponse.jsonPath().getInt("[0].id");
        ctx.response = ApiClient.getAccount(ctx.accountId);
    }

    @Then("the response should contain a list of accounts")
    public void theResponseShouldContainAListOfAccounts(){
        List<?> accounts = ctx.response.jsonPath().getList("$");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @And("the account should have required fields")
    public void theAccountShouldHaveRequiredFields(){
        assertNotNull(ctx.response.jsonPath().get("id"));
        assertNotNull(ctx.response.jsonPath().get("customerId"));
        assertNotNull(ctx.response.jsonPath().get("balance"));
        assertNotNull(ctx.response.jsonPath().get("type"));
    }
}
