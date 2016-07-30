Feature: Get examples

  Scenario: Return existing examples
    Given there are some examples
      | Hello world       |
      | Hello world again |
    When I get all examples
    Then all examples are found
      | Hello world       |
      | Hello world again |

  Scenario: Return no examples
    Given there are no examples
    When I get all examples
    Then no examples are found