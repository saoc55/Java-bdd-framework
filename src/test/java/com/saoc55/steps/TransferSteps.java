package com.saoc55.steps;

import com.saoc55.utils.ApiClient;
import com.saoc55.utils.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TransferSteps {

    private final ScenarioContext ctx;

    public TransferSteps(ScenarioContext ctx){
        this.ctx = ctx;
    }

    @Given("I have at least two accounts")
    public void iHaveAtLeastTwoAccounts() {
        Response accountsResponse = ApiClient.getAccounts(ctx.customerId);
        List<Integer> accountIds = accountsResponse.jsonPath().getList("id");

        if (accountIds.size() < 2) {
            // Create a savings account if fewer than 2 exist
            ApiClient.baseRequest()
                    .queryParam("customerId", ctx.customerId)
                    .queryParam("newAccountType", 1)
                    .queryParam("fromAccountId", accountIds.get(0))
                    .post("/createAccount");

            // Refresh account list
            accountIds = ApiClient.getAccounts(ctx.customerId)
                    .jsonPath().getList("id");
        }

        assertTrue(accountIds.size() >= 2,
                "Expected at least 2 accounts but found: " + accountIds.size());
        ctx.accountId = accountIds.get(0);
        ctx.secondAccountId = accountIds.get(1);
    }

    @When("I transfer 100 from the first account to the second account")
    public void iTransfer100FromFirstAccountToSecond(){
        ctx.response = ApiClient.transfer(ctx.accountId, ctx.secondAccountId, 100);
    }

}
