Feature: Search Contact Functionality

  @TC_Contacts
  Scenario: TC_Contacts_23 - Search contact by name

    Given User should be logged in
    And the user navigates to the contacts section

    When user searches for "John"
    Then correct contact "John" should be displayed