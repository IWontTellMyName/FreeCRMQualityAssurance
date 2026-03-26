@deals1
Feature: User wants to view and search deals to quickly find and manage relevant opportunities

  Scenario Outline: To validate that Global Search returns the correct deal by deal name
    Given User should be logged in
    When the user clicks on the Deals tab in the main navigation menu
    Then the user should be on the Deals page
    When the user enters "<searchTerm>" in the global search box and clicks the search icon
    Then the user should see a list of Records matching the search term
    When the user clicks on Deals in the Records
    Then the user should see a list of Deals matching the "<searchTerm>"
    Examples:
      | searchTerm |
      | Quantum |

  Scenario Outline: To validate that filters (Stage, Status, Close Date, Source, Tags etc.) return correctly filtered deals
    When the user clicks on the show filter button and selects Stage from the dropdown
    And the user selects "<stage>" from the Stage options and applies the filter
    Then the user should see a list of Deals that are in the "<stage>" stage
    Examples:
      | stage        |
      | Qualify  |


  Scenario: To validate that incorrect filters return empty lists.
    When the user clicks on the show filter button and selects Title from the dropdown
    And the user enters "NonExistentTitle" in the Title filter and applies the filter
    Then the user should see an empty list of Deals, indicating no matches found

