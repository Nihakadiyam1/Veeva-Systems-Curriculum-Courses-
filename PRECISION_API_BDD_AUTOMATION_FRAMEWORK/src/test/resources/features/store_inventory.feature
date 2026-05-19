Feature: Inventory Validation

  Scenario: Validate inventory count
    When I fetch inventory
    Then inventory should match available pets