Feature: All test cases are list all Customer by user id  related endpoint

  @listAllCustomerUserId
  Scenario Outline: Verify list list all ListAllCustomeruserid with valid payload
    Given A user has logged in access with ListAllCustomeruserid
    When Extract user id from the list to test ListAllCustomeruserid
    And ListAllCustomeruserid  api called
    And isApiHandled is verified as "<isApiHandled>" to test ListAllCustomeruserid
    And isRequestSuccess is verified as "<isRequestSuccess>" to test ListAllCustomeruserid
    And statusCode is verified as <statusCode> to test list ListAllCustomeruserid
    And message is verified "<message>" to test list ListAllCustomeruserid

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |