@deals
Feature: Integration Testing for Deals with other modules like contacts, Company, Tasks, products etc.

  @aut_SudeshnaPathak
  Scenario Outline: To validate that creating a deal with associated contact and company works correctly
    Given User should be logged in with "<LoginRow>"
    And User is on the Deals page
    When the user opens the deal "<DealTitle>"
    And edits the deal
    And associates contact, company, and product using "<AssociatedEntityRow>" from "<SheetName>"
    And clicks save
    And associates task using "<AssociatedEntityRow>" from "<SheetName>"
    And associates event using "<AssociatedEntityRow>" from "<SheetName>"
    And clicks save
    Then User is on the Deals page
    When the user opens the deal "<DealTitle>"
    Then the deal should be updated successfully with the associated contact, company, product, task and event
    Then the event should be visible in the calendar
    Examples:
      | LoginRow | DealTitle | AssociatedEntityRow | SheetName |
      | 2        | Test Deal  | 2                  | Deals     |