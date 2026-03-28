Feature: Validate mandatory fields in task creation
  @TC_Task
  Scenario Outline: Validate task creation with empty title
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu
    And the user clicks the create button to open the task creation form
    Then the task creation form should be displayed

    When the user leaves the task title empty
    And clicks on Save

    Then an error message "The field Title is required." should be displayed
    And the task should not be created
    Examples:
      | LoginRow |
      | 4        |