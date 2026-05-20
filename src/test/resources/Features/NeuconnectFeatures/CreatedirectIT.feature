Feature: Create IT API Testing

  @CreateIT
  Scenario Outline: Verify user can create IT successfully

    Given User login for create IT
    And Admin login for create IT
    And Extract itr id from itr list
    And Set payload for create IT
    And Create IT api called
    And List Direct it api called
    And isApiHandled is verified as "<isApiHandled>" to test CreateInventoryTransfer
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list CreateInventoryTransfer
    And statusCode is verified as <statusCode> to test list CreateInventoryTransfer
    And message is verified "<message>" to test list CreateInventoryTransfer

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |