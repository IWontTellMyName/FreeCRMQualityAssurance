Feature: Search Task Functionality

  @TC_Task
  Scenario: Search existing task and verify results
    Given User should be logged in
    When the user searches for task "task"
    Then relevant task results should be displayed