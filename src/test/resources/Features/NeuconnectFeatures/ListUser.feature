Feature: All test cases are list user related endpoint

  @listUser
  Scenario Outline: Verify list all user with valid payload
    Given A user has logged in access with list user
    When List user api called
    And isApiHandled is verified as "<isApiHandled>" to test ListUser
    And isRequestSuccess is verified as "<isRequestSuccess>" to test listUser
    And statusCode is verified as <statusCode> to test list user
    And message is verified "<message>" to test list user

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
