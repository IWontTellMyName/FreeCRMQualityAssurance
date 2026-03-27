Feature: Create Task
  @TC_Task
  Scenario: Create a task with valid inputs and verify in task list
    Given User should be logged in
    And the user clicks on the Tasks tab in the main navigation menu
    And the user clicks the create button to open the task creation form
    Then the task creation form should be displayed
    When the user enters task title "demo"
    And selects a valid due date
    And clicks on Save

    Then the task should be created successfully

    When the user navigates to the task list
    Then the created task "demo" should be visible in the task list
