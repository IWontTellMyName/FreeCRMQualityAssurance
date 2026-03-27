Feature: Contact Validation - Invalid Email

  @TC_Contacts
  Scenario: Invalid email format - no validation applied (bug)

    Given User should be logged in
    And the user navigates to the contacts section

    When user clicks on create button
    And user enters first name "Jonny"
    And user enters last name "paul"
    And user enters invalid email "abc123"
    And user clicks on save button

    Then contact should be created successfully with invalid email