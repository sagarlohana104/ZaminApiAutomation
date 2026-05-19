Feature: All test cases are GetDocumentSeries related endpoint

  @GetDocumentSeries
  Scenario Outline: Verify list all GetDocumentSeries with valid payload
    Given A user has logged in access with GetDocumentSeries
    When add documentType with valid type
    And GetDocumentSeries  api called
    And isApiHandled is verified as "<isApiHandled>" to test GetDocumentSeries
    And isRequestSuccess is verified as "<isRequestSuccess>" to test GetDocumentSeries
    And statusCode is verified as <statusCode> to test list GetDocumentSeries
    And message is verified "<message>" to test list GetDocumentSeries

    Examples:
      | isApiHandled | isRequestSuccess | statusCode | message |
      | TRUE         | TRUE             | 200        | Success |