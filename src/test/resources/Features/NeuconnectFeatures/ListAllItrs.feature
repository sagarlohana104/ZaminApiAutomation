Feature: All test cases are list itrs related endpoint

  @ListAllItrs
  Scenario Outline: Verify list itrs with valid payload
    Given A user has logged in access with list all Itrs
    When List Itrs user id  api called
    And isApiHandled is verified as "<isApiHandled>" to test List Itrs
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list Itrs
    And statusCode is verified as <statusCode> to test list Itrs
    And message is verified "<message>" to test list Itrs

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
