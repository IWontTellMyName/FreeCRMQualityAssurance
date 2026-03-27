Feature: Verify Contacts List Display

  @TC_Contacts
  Scenario: TC_Contacts_01 - Verify contacts list is displayed on the contacts page
    Given User should be logged in
    And the user navigates to the contacts section
    Then the contacts list should be displayed on the contacts page