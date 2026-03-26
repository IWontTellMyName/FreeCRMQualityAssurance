Feature: Create Contact Functionality

  @TC_Contacts_08
  Scenario: TC_Contacts_08 - Create a new contact with valid data

    Given User should be logged in
    And the user navigates to the contacts section

    When user clicks on create button
    And user enters contact details
    And user clicks on save button

    Then user should be navigated to new contact page