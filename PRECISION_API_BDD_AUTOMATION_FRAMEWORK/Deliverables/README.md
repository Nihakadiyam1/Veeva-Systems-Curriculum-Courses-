API Test Automation Framework
1. Project Overview

This project implements an API Test Automation Framework for validating REST APIs using Java and REST Assured.
The framework follows a structured approach with Client pattern, Base classes, and BDD using Cucumber to ensure maintainable and scalable test automation.

The tests automate CRUD operations on APIs and validate responses using assertions and logging.

2. Tech Stack
   Programming Language: Java
   API Automation: REST Assured
   BDD Framework: Cucumber
   Test Runner: TestNG
   Build Tool: Maven
   Reporting: Extent Reports
   Logging: Log4j 
   Version Control: Git

3. Project Structure

      API-Test-Automation
      |
      |--src/test/java
      |  |--client/PetClient.java          # API client classes for endpoints
      |  |--base/BaseTest.java             # Base test classes
      |  |--stepdefinitions/PetSteps.java  # Cucumber step definitions
      |  |--tests/PetTests.java            # Test runner classes
      |  |--utils/log4j2.xml
      |
      |--src/test/resources
      |   |--features/pet.feature          # Cucumber feature files
      |
      |--pom.xml             # Maven dependencies
      |--README.md

4. Prerequisites

Before running the project, ensure the following are installed:

  * Java JDK 8 or higher
  * Maven 
  * IDE (IntelliJ)
  * Git

5. How to Run

   1. Open IntelliJ IDEA
   2. Install Maven dependencies
      mvn clean install
   3. Run tests using Maven
      mvn test
   4. Run specific Cucumber feature files
      Use a TestRunner class with @CucumberOptions pointing to the features directory.
   5. View reports
      Extent Reports: target/ExtentReports.html
      TestNG Reports: target/surefire-reports/index.html

6. Key Features

   Dynamic Test Data: All test data passed through Cucumber feature files
   Reusable API Client Classes: Encapsulation of endpoints for maintainability
   BDD Approach: Readable, data-driven test scenarios
   Logging: Configured with Log4J for traceable execution
   Reporting: Execution reports for quick analysis
   Cross-Endpoint Validation: Ensures API responses are consistent across calls
   Scalable: Easy to add new endpoints and test cases

7. Test Scenarios

      1. Pet Lifecycle (CRUD)
         Create, Read, Update, Delete a pet
         Validate response codes and data correctness
      2. Inventory Analysis
         Fetch inventory counts
         Compare with live pet list
      3. Negative User Testing
         Invalid user creation
         Invalid login
         Validate error responses
      4. Cross-Endpoint Data Consistency
         Create pet → Update status → Validate in inventory & findByStatus

8. Reporting
   Extent Reports are generated after execution
   Report location:
   target/ExtentReport.html
   Cucumber Report is generated after execution
   Report Location
   target/CucumberReport.html

9. Logging info is seen in terminal