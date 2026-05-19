@pet @crud
Feature: Pet Lifecycle Management (CRUD Operations)

  Scenario Outline: Complete lifecycle of a pet - Create, Read, Update, Delete
    Given I have a pet with name "<petName>" and status "<initialStatus>"
    When I create the pet via POST /pet
    Then the response status code should be 200
    And I extract the pet ID from the response

    When I retrieve the pet via GET /pet/{petId}
    Then the response status code should be 200
    And the pet name should be "<petName>"
    And the pet status should be "<initialStatus>"

    When I update the pet status to "<updatedStatus>" via PUT /pet
    Then the response status code should be 200
    And the pet status should be "<updatedStatus>"

    When I delete the pet via DELETE /pet/{petId}
    Then the response status code should be 200

    When I retrieve the pet via GET /pet/{petId}
    Then the response status code should be 404

    Examples:
      | petName    | initialStatus | updatedStatus |
      | Buddy      | available     | sold          |
      | Max        | pending       | sold          |
      | Charlie    | available     | pending       |
