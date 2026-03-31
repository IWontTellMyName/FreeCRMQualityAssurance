Feature: Validate task title length limit
  @TC_Task
  Scenario Outline: Validate task title length limit
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu
    And the user clicks the create button to open the task creation form
    Then the task creation form should be displayed
    When the user enters a task title of length 260
    And clicks on Save
    Then a length error message "Title is longer than 250 characters" should be displayed
    Examples:
      | LoginRow |
      | 4        |