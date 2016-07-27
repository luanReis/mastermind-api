Feature: Meet My Requirement

  Scenario: Setup cucumber
    Given there are some examples
      | Hello world       |
      | Hello world again |
    When I get all examples
    Then all examples are found
      | Hello world       |
      | Hello world again |