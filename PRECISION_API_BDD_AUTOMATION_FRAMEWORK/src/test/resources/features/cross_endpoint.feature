@crossendpoint @integration
Feature: Cross-Endpoint Data Consistency

  Scenario Outline: Verify pet status change reflects across endpoints
    Given I have a pet with category "<categoryName>" and status "available"
    When I create the pet via POST /pet
    Then the response status code should be 200
    And I extract the pet ID from the response

    When I update the pet status to "sold" via PUT /pet
    Then the response status code should be 200

    When I fetch the store inventory via GET /store/inventory
    Then the response status code should be 200

    When I fetch pets by status "sold" via GET /pet/findByStatus
    Then the response status code should be 200
    And the created pet should exist in the sold pets list using streams

    Examples:
      | categoryName      |
      | HighValueBulldog  |
      | PremiumPoodle     |
      | LuxuryLabrador    |
