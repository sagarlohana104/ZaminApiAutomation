Feature: All test cases are listAllSalesOrder related endpoint

  @listAllSalesOrder
  Scenario Outline: Verify list all listAllSalesOrder with valid payload
    Given A user has logged in access with list listAllSalesOrder
    When listAllSalesOrder api called
    And isApiHandled is verified as "<isApiHandled>" to test listAllSalesOrder
    And isRequestSuccess is verified as "<isRequestSuccess>" to test listAllSalesOrder
    And statusCode is verified as <statusCode> to test listAllSalesOrder
    And message is verified "<message>" to test listAllSalesOrder

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
