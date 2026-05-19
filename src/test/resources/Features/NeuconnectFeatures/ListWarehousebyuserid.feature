Feature: All test cases are list user related endpoint

  @listwarehousebyuserid
  Scenario Outline: Verify list all warehouse by user id with valid payload
    Given A user has logged in access with list all warehouse by user id
    And user id extract from the user list
    When List warehouse user id  api called
    And isApiHandled is verified as "<isApiHandled>" to test List warehouse by user id
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list warehouse by user id
    And statusCode is verified as <statusCode> to test list warehouse by user id
    And message is verified "<message>" to test list warehouse by user id

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
