Feature: Authentication
  As a Parabank user
  I want to authenticate via the API
  So that I can access my account data

  Scenario: Successful login with valid credentials
    Given I have valid credentials
    When I send a login request
    Then the response status should be 200
    And the response should contain a customer ID

  Scenario: Failed login with invalid credentials
    Given I have invalid credentials
    When I send a login request
    Then the response status should be 400