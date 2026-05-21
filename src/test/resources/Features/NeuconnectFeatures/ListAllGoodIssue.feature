Feature: All test cases are list goods issue related endpoint

  @ListAllGoodsissue
  Scenario Outline: Verify list goods issue with valid payload
    Given A user has logged in access with list all goods issue
    When List goods issue user id  api called
    And isApiHandled is verified as "<isApiHandled>" to test List goods issue
    And isRequestSuccess is verified as "<isRequestSuccess>" to test listgoodsissue
    And statusCode is verified as <statusCode> to test listgoodsissue
    And message is verified "<message>" to test listgoodsissue

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |
