package com.saoc55.steps;

import com.saoc55.utils.ApiClient;
import com.saoc55.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class AuthSteps {

    private final ScenarioContext ctx;
    private String username;
    private String password;

    public AuthSteps(ScenarioContext ctx){
        this.ctx = ctx;
    }

    @Given("I have valid credentials")
    public void iHaveValidCredentials(){
        username = ApiClient.VALID_USERNAME;
        password = ApiClient.VALID_PASSWORD;
    }

    @Given("I have invalid credentials")
    public void iHaveInvalidCredentials(){
        username = ApiClient.INVALID_USERNAME;
        password  =ApiClient.INVALID_PASSWORD;
    }

    @When("I send a login request")
    public void iSendLoginRequest(){
        ctx.response = ApiClient.login(username, password);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus){
        assertEquals(expectedStatus, ctx.response.getStatusCode());
    }

    @And("the response should contain a customer ID")
    public void theResponseContainsStatusCode(){
        assertNotNull(ctx.response.jsonPath().get("id"));
    }

}
