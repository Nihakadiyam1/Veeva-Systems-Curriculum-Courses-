@user @negative
Feature: User Error Handling

  Scenario Outline: Validate error messages for invalid user actions
    When I get user with username "<invalidUser>"
    Then the response status should be 404
    And the message should contain "User not found"
    When I login with username "wrongUser" and password "wrongPass"
    Then the response should not contain a valid session

    Examples:
      | invalidUser      |
      | ghost_user_9999  |