# java-bdd-framework

![Java](https://img.shields.io/badge/Java-17-orange)
![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-brightgreen)
![RestAssured](https://img.shields.io/badge/RestAssured-5.5.0-blue)
![CI](https://github.com/saoc55/java-bdd-framework/actions/workflows/maven.yml/badge.svg)

BDD test framework for the Parabank REST API using Java, Cucumber, and RestAssured.

## Stack

- Java 17
- Cucumber 7 (Gherkin BDD)
- RestAssured 5.5 (HTTP client)
- JUnit 5 Platform (test runner)
- Allure (reporting)
- PicoContainer (step definition DI)
- GitHub Actions (CI)

## Test Coverage

| Feature       | Scenarios |
|---------------|-----------|
| Authentication | Login valid/invalid credentials |
| Account Management | List accounts, get account by ID |
| Fund Transfers | Transfer between accounts |

## Project Structure
src/test/

├── java/com/saoc55/

│   ├── runners/      # JUnit Platform suite runner

│   ├── steps/        # Cucumber step definitions

│   └── utils/        # ApiClient + ScenarioContext

└── resources/

└── features/     # Gherkin feature files

## Run Locally

```bash
mvn test
```

## Test Target

[Parabank](https://parabank.parasoft.com) — open-source banking demo app used across all projects in this portfolio for a consistent end-to-end narrative.
