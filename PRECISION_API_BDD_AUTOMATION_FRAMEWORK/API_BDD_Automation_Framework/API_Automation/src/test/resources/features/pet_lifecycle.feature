@pet @regression
Feature: Pet Lifecycle Management

  Scenario Outline: Create, Read, Update, and Delete a pet
    Given I create a pet with id <id> and name "<name>" and status "available"
    Then the response status should be 200
    When I get pet with id <id>
    Then the pet name should be "<name>" and status should be "available"
    When I update pet with id <id> and status "sold"
    Then the response status should be 200
    And I delete pet with id <id>
    Then the response status should be 200

    Examples:
      | id    | name       |
      | 77881 | Razzmatazz |
      | 77882 | Buddy      |