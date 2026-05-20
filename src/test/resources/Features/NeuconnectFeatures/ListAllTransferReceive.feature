Feature: All test cases are list trs related endpoint

  @ListAlltrs
  Scenario Outline: Verify ListAllTransferReceive with valid payload
    Given A user has logged in access with list all ListAllTransferReceive
    When List ListAllTransferReceive user id  api called
    And isApiHandled is verified as "<isApiHandled>" to test List ListAllTransferReceive
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list ListAllTransferReceive
    And statusCode is verified as <statusCode> to test list ListAllTransferReceive
    And message is verified "<message>" to test list ListAllTransferReceive

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
