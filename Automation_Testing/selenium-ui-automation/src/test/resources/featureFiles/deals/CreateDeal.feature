@deals
Feature: User, wants to create a deal to track potential sales opportunities

  @aut_SudeshnaPathak
  Scenario Outline: User creates a deal with valid information
    Given User should be logged in with "<LoginRow>"
    And User is on the Deals page
    When User clicks on the Create Deal button
    And User enters "<DealRow>" and "<SheetName>" to fill in valid information
    And clicks the Save button
    Then the deal should be created successfully
    Examples:
      | LoginRow | DealRow | SheetName |
      | 2       | 2         | Deals     |

  @aut_SudeshnaPathak
  Scenario: User tries to create a deal with missing required fields or invalid inputs
    Given the user is the Deals page
    When User clicks on the Create Deal button
    And the user leaves one or more required fields empty
    And clicks the Save button
    Then the deal should not be created successfully
    And the user should see an error message indicating which fields are required
    When the user fills in the missing required fields with valid data
    And the user enters invalid data in one or more fields
    And clicks the Save button
    Then the deal should not be created successfully
    And the user should see an error message indicating which fields contain invalid data


