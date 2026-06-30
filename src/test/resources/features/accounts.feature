Feature: Account Management
  As a Parabank customer
  I want to retrieve my account information
  so that i can monitor my finances

  Background:
    Given I am logged in as a valid user

  Scenario: Retrieve all accounts for a customer
    When I request all accounts for the customer
    Then the response status should be 200
    And the response should contain a list of accounts

  Scenario: Retrieve a single account by ID
    When I request a single account by ID
    Then the response status should be 200
    And the account should have required fields

