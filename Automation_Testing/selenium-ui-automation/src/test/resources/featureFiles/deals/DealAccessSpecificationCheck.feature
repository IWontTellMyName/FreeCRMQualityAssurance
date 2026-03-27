@deals
Feature: Deal Access Control for Private Deals

  @aut_SudeshnaPathak
  Scenario Outline: Verify private deal visibility for allowed and restricted users

    # Step 1: Create private deal with restricted access
    Given User should be logged in with "<CreatorRow>"
    And User is on the Deals page
    When User clicks on the Create Deal button
    And User enters "<DealRow>" and "<SheetName>" to fill in valid information
    And sets Access to Private
    And adds user "<AllowedUserRow>" in Select users allowed access
    And clicks the Save button
    Then the deal should be created successfully
    And User logs out

    # Step 2 & 3: Login as allowed user and verify visibility
    Given User should be logged in with "<AllowedUserRow>"
    And User is on the Deals page
    When the user clicks on the show filter button and selects Title from the dropdown
    And the user enters "<DealTitle>" in the Title filter and applies the filter
    Then the "<DealTitle>" should be visible in the results
    And User logs out

    # Step 4 & 5: Login as restricted user and verify no visibility
    Given User should be logged in with "<RestrictedUserRow>"
    And User is on the Deals page
    When the user clicks on the show filter button and selects Title from the dropdown
    And the user enters "<DealTitle>" in the Title filter and applies the filter
    Then the "<DealTitle>" should not be visible in the results
    And User logs out

    Examples:
      | CreatorRow | DealRow | SheetName | AllowedUserRow | RestrictedUserRow | DealTitle |
      | 2          | 3      | Deals     | 5              | 4                 | Access Control Test Deal |