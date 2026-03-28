Feature: Verify Contacts List Display

  @TC_Contacts
  Scenario Outline: TC_Contacts_01 - Verify contacts list is displayed on the contacts page
    Given User should be logged in with "<LoginRow>"
    And the user navigates to the contacts section
    Then the contacts list should be displayed on the contacts page
    Examples:
      | LoginRow |
      |3         |