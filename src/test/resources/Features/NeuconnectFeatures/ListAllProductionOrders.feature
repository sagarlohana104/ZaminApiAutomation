Feature: All test cases are list Production orders issue related endpoint

  @ListAllProductionorders
  Scenario Outline: Verify list production orders with valid payload
    Given A user has logged in access with list all production order
    When List  production order user id  api called
    And isApiHandled is verified as "<isApiHandled>" to test List production order
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list production order
    And statusCode is verified as <statusCode> to test list production order
    And message is verified "<message>" to test list production order

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
