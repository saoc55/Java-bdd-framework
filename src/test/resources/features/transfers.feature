Feature: Fund Transfers
  As a Parabank customer
  I want to transfer funds between accounts
  So that I can manage my money

  Background:
    Given I am logged in as a valid user

  Scenario: Successful fund transfer between accounts
    Given I have at least two accounts
    When I transfer 100 from the first account to the second account
    Then the response status should be 200