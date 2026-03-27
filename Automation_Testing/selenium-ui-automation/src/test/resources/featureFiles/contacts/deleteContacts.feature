Feature: Delete Contact Functionality

  @TC_Contacts
  Scenario Outline: TC_Contacts_20 - Delete a contact successfully

    Given User should be logged in with "<LoginRow>"
    And the user navigates to the contacts section

    When user deletes contact "Debashis Mandal"

    Then contact "Debashis Mandal" should be deleted
    Examples:
      | LoginRow |
      |3         |