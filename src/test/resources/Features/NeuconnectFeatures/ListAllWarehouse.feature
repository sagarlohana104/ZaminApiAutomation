Feature: All test cases are list all warehouse related endpoint

  @listAllWarehouse
  Scenario Outline: Verify list list all warehouses with valid payload
    Given A user has logged in access with list all warehouse
    When List all warehouses api called
    And isApiHandled is verified as "<isApiHandled>" to test warehouses
    And isRequestSuccess is verified as "<isRequestSuccess>" to test warehouses
    And statusCode is verified as <statusCode> to test list warehouses
    And message is verified "<message>" to test list warehouses

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
