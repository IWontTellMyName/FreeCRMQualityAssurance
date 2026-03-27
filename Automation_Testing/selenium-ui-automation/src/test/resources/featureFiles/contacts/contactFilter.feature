Feature: Contact Filter Functionality

  @TC_Contacts
  Scenario: Filter contact by first name and validate result

    Given User should be logged in
    And the user navigates to the contacts section

    When user clicks on show filters button
    And user clicks on search dropdown
    And user selects "First Name" from dropdown
    And user enters value "suvam"
    And user clicks on filter button

    Then filtered result should display contact "suvam"