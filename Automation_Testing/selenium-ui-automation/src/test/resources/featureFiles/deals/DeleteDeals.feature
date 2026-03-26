@deals
Feature: User wants to delete a deal to remove outdated or irrelevant entries from the system

  Scenario Outline: To validate that a user can delete a deal successfully
    Given User should be logged in
    And User is on the Deals page
    When the user selects a "<dealTitle>" deal from the list of deals and clicks on the delete icon
    And cancels the delete action in the confirmation dialog
    Then the "<dealTitle>" should still be present in the list of deals
    When the user selects the same "<dealTitle>" deal again and clicks on the delete icon
    And confirms the delete action in the confirmation dialog
    Then the "<dealTitle>" should be removed from the list of deals
    Examples:
      | dealTitle |
      | New Deal  |

