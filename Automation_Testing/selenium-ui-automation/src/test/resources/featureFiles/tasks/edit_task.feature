Feature: Edit Task
  @TC_Task
  Scenario Outline: Edit an existing task successfully
    Given User should be logged in with "<LoginRow>"
    And the user clicks on the Tasks tab in the main navigation menu
    And the user selects an existing task
    Then the task details page should be displayed

    When the user clicks on Edit
    And modifies the task title to "New_Task"
    And clicks on Save after editing

    Then the task should be updated successfully
    And the updated title "New_Task" should be displayed
    Examples:
      | LoginRow |
      | 4        |