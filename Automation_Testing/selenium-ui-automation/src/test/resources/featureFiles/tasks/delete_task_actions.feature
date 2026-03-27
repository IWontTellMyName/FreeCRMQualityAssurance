Feature: Delete Task
  @TC_Task_002
  Scenario Outline: Cancel delete should not remove task
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu

    When the user clicks on Delete
    And the user clicks on Cancel delete

    Then the task should still exist
    Examples:
      | LoginRow |
      | 4        |
