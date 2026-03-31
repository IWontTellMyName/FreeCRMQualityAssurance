Feature: Create Task
  @TC_Task
  Scenario Outline: Create a task with valid inputs and verify in task list
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu
    And the user clicks the create button to open the task creation form
    Then the task creation form should be displayed
    When User enters "<TaskRow>" and "<SheetName>" to fill in valid information
    And clicks on Save
    Then the task should be created successfully
    When the user navigates to the task list
    Then the created task "Task" should be visible in the task list
    Examples:
      | LoginRow | TaskRow | SheetName |
      | 4        | 2       | Tasks     |
