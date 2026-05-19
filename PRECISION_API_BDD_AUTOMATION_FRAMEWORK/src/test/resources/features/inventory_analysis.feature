@store @inventory
Feature: Inventory Analysis and Validation

  Scenario Outline: Verify inventory count matches available pets
    When I fetch the store inventory via GET /store/inventory
    Then the response status code should be 200
    And I extract the count of pets with status "<status>"

    When I fetch pets by status "<status>" via GET /pet/findByStatus
    Then the response status code should be 200
    And the number of pets returned should match the inventory count

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |
