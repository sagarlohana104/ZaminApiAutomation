Feature: CreateInventoryTransferRequest API

  @CreateInventoryTransferRequest
  Scenario Outline: Verify CreateInventoryTransferRequest with valid payload

    Given A user has logged in access with CreateInventoryTransferRequest
    And admin logged in access with create itr

    And User id extract from user list to test itr
    And Extract warehouse from warehouse list to test create itr
    And Extract to warehouse from warehouse list to test create itr

    And Extract items code by user id from list all itemcode to test create itr
    And Create itr with valid payload
    And CreateInventoryTransferRequest user id api called

    And isApiHandled is verified as "<isApiHandled>" to test CreateInventoryTransferRequest
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list CreateInventoryTransferRequest
    And statusCode is verified as <statusCode> to test list CreateInventoryTransferRequest
    And message is verified "<message>" to test list CreateInventoryTransferRequest

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |