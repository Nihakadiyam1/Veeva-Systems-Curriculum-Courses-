Feature: Pet Lifecycle

  Scenario Outline: CRUD lifecycle of pet
    Given I create a pet with status "<status>"
    When I get the pet
    Then validate pet details
    When I update pet to "<updatedStatus>"
    And I delete the pet
    Then pet should be deleted

    Examples:
      | status    | updatedStatus |
      | available | sold          |