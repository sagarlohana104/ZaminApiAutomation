Feature: CreateTransferRequest API

  @CreateTransferReceive
  Scenario Outline: Verify CreateTransferReceive with valid payload
    Given A user has logged in access with CreateTransferReceive
    And admin logged in access with CreateTransferReceive
    And User id extract from user list to test itr to test CreateTransferReceive
    And Extract warehouse from warehouse list to test CreateTransferReceive
    And Extract to warehouse from warehouse list to test CreateTransferReceive
    And Extract items code by user id from list all itemcode to test CreateTransferReceive
    And Create direct TR with valid payload
    And CreateTransferReceive user id api called
    And list TRS api called
    And isApiHandled is verified as "<isApiHandled>" to test CreateTransferReceive
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list CreateTransferReceive
    And statusCode is verified as <statusCode> to test list CreateTransferReceive
    And message is verified "<message>" to test list CreateTransferReceive

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |