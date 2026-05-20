Feature: Create IT API Testing

  @ListDirectIT
  Scenario Outline: Verify user can List IT successfully

    Given A user has logged in access with CreateInventoryTransfer
    And List All direct IT
    And isApiHandled is verified as "<isApiHandled>" to test ListInventoryTransfer
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list ListInventoryTransfer
    And statusCode is verified as <statusCode> to test list ListInventoryTransfer
    And message is verified "<message>" to test list ListInventoryTransfer

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |