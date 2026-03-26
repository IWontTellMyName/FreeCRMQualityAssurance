Feature: Delete Contact Functionality

  @TC_Contacts_20
  Scenario: TC_Contacts_20 - Delete a contact successfully

    Given User should be logged in
    And the user navigates to the contacts section

    When user deletes contact "John Doe"

    Then contact "John Doe" should be deleted