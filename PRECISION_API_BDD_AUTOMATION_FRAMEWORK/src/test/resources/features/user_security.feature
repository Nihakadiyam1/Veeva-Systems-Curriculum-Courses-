@user @security @negative
Feature: User Security and Error Handling

  Scenario Outline: Create user with invalid email format
    Given I have a user with username "<username>" and invalid email "<invalidEmail>"
    When I create the user via POST /user
    Then the response status code should be 200

    Examples:
      | username       | invalidEmail   |
      | testuser1      | invalid_email  |
      | testuser2      | no_at_symbol   |
      | testuser3      | @nodomain      |

  Scenario: Fetch non-existent user returns 404
    When I attempt to fetch user "nonExistentUser123456789" via GET /user/{username}
    Then the response status code should be 404
    And the response message should contain "User not found"

  Scenario Outline: Login with incorrect credentials fails
    When I attempt to login with username "<username>" and password "<password>"
    Then the response should not contain a valid session token

    Examples:
      | username        | password        |
      | wronguser123    | wrongpass123    |
      | fakeuser        | fakepassword    |
