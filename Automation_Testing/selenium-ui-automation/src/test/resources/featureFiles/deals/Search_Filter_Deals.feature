Feature: User wants to view and search deals to quickly find and manage relevant opportunities

  Scenario: Global Search for Deals
    Given User should be logged in
    When the user clicks on the Deals tab in the main navigation menu
    And the user enters "<searchTerm>" in the global search box and clicks the search icon
    Then the user should see a list of Records matching the search term
    When the user clicks on Deals in the Records
    Then the user should see a list of Deals matching the search term