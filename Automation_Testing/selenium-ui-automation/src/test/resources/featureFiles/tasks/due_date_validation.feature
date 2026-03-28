Feature: Task Due Date Validation

  @TC_Task
  Scenario Outline: Validate task creation with past due date
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu
    And the user clicks the create button to open the task creation form
    Then the task creation form should be displayed

    When the user enters task title "Demo Task"
    And the user selects due date "01.01.2020" as a past date
    And clicks on Save

    Then a validation error message should be displayed
    And the task should not be created by due date
    Examples:
      | LoginRow |
      | 4        |