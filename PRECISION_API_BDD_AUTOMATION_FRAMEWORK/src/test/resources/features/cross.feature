Feature: Cross Endpoint Validation

  Scenario: Validate pet exists in sold list
    Given I create a pet with status "available"
    When I update pet to "sold"
    And I fetch inventory
    Then pet should exist in sold list