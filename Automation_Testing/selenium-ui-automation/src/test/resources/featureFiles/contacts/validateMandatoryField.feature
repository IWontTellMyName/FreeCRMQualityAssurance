Feature: Contact Validation - Mandatory Fields

  @TC_Contacts_10
  Scenario: Error when mandatory fields are blank

    Given User should be logged in
    And the user navigates to the contacts section

    When user clicks on create button and user clicks on save button without entering mandatory fields

    Then error message for first name should be displayed
    And error message for last name should be displayed