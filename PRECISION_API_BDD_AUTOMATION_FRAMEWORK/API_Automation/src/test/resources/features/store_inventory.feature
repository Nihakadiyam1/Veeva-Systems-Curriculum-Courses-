@store @inventory
Feature: Store Inventory Validation

  Scenario: Verify inventory counts match pet status lists
    When I get store inventory
    Then inventory count for "available" should match the total pets found by status