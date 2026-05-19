Feature: All test cases are list all warehouse related endpoint

  @addWarehousetouser
  Scenario Outline: Verify list list all warehouses with valid payload
    Given A user has logged in access with list all addwarehousetouser
    And User id extract from user list
    And Extract warehouse from warehouse list
    And extact warehouse to from warehouse list
    When add warehouse to user api called
    And isApiHandled is verified as "<isApiHandled>" to test addwarehousetouser
    And isRequestSuccess is verified as "<isRequestSuccess>" to test addwarehousetouser
    And statusCode is verified as <statusCode> to test list addwarehousetouser
    And message is verified "<message>" to test list addwarehousetouser

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 201       | Success |
