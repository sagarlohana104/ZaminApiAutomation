Feature: CreateSalesOrder API

  @CreateSalesOrder
  Scenario Outline: Verify CreateSalesOrder with valid payload

    Given A user has logged in access with CreateSalesOrder
    And admin logged in access with CreateSalesOrder

    And User id extract from user list to test CreateSalesOrder
    And Extract warehouse from warehouse list to test CreateSalesOrder
    And Extract item code from item list to test CreateSalesOrder
    And Extract customerReferenceId from customer list using user id

    And CreateSalesOrder  with valid payload
    And CreateSalesOrder user id api called
    And CreateSalesOrder  list api

    And isApiHandled is verified as "<isApiHandled>" to test CreateSalesOrder
    And isRequestSuccess is verified as "<isRequestSuccess>" to test list CreateSalesOrder
    And statusCode is verified as <statusCode> to test list CreateSalesOrder
    And message is verified "<message>" to test list CreateSalesOrder

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 201        | Sale Order Created Successfully |