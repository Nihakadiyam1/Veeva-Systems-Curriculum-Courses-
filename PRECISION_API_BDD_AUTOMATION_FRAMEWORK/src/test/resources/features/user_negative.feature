Feature: User Negative Testing

  Scenario: Invalid user handling
    Given I create user with invalid email
    When I fetch non existing user
    Then user should not exist
    When I login with invalid credentials
    Then no session token should be returned