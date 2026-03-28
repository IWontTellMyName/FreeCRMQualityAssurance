Feature: Search Task Functionality

  @TC_Task
  Scenario Outline: Search existing task and verify results
    Given User should be logged in with "<LoginRow>"
    When the user searches for task "task"
    Then relevant task results should be displayed
    Examples:
      | LoginRow |
      | 4        |