Feature: Create Goods Issue API Testing

  @CreateGoodissue
  Scenario Outline: Verify user can create goods issue successfully

    Given User login for create goods issue
    And Admin login for create goods issue
    And Extract warehouse code from warehouse list for goods issue
    And Extract user id from user list for item code
    And Extract item code from item list for goods issue
    And Set payload for create goods issue
    And Create goods issue api called
    And isApiHandled is verified as "<isApiHandled>" to test goods issue
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list goods issue
    And statusCode is verified as <statusCode> to test list goods issue
    And message is verified "<message>" to test list goods issue


    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |

