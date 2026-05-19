Feature: All test cases are list all item by user id  related endpoint

  @listAllItemByUserId
  Scenario Outline: Verify list list all ListAllItembyuserid with valid payload
    Given A user has logged in access with ListAllItembyuserid
    When Extract user id from the list to test ListAllItembyuserid
    And ListAllItembyuserid  api called
    And isApiHandled is verified as "<isApiHandled>" to test ListAllItembyuserid
    And isRequestSuccess is verified as "<isRequestSuccess>" to test ListAllItembyuserid
    And statusCode is verified as <statusCode> to test list ListAllItembyuserid
    And message is verified "<message>" to test list ListAllItembyuserid

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
