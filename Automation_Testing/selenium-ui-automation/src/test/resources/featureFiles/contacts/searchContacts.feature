Feature: Search Contact Functionality

  @TC_Contacts
  Scenario Outline: TC_Contacts_23 - Search contact by name

    Given User should be logged in with "<LoginRow>"
    And the user navigates to the contacts section

    When user searches for "Debashis"
    Then correct contact "Debashis" should be displayed
    Examples:
      | LoginRow |
      |3         |