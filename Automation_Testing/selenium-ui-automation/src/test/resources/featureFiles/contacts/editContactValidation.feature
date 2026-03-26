Feature: Contact Validation - Edit Contact Mandatory Field

  @TC_Contacts_19
  Scenario: Error when mandatory field is cleared during edit

    Given User should be logged in
    And the user navigates to the contacts section
    When user clicks on edit icon for "suvam nath"
    And user clears the last name field
    And user clicks on save button
    Then error message for last name should be displayed