Feature: Create Contact Functionality

  @TC_Contacts
  Scenario Outline: TC_Contacts_08 - Create a new contact with valid data

    Given User should be logged in with "<LoginRow>"
    And the user navigates to the contacts section

    When user clicks on create button
    And user enters contact details using "<RowNumber>" and "<SheetName>"
    And user clicks on save button

    Then user should be navigated to new contact page
    Examples:
      | RowNumber | SheetName | LoginRow |
      | 2 | Contacts |     3|