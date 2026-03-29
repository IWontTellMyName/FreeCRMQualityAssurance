@deals
Feature: User wants to create and view deal pipeline and reporting to track progress and performance across deal stages
  Scenario Outline: To validate that the deal pipeline view displays the correct group of deals based on the selected filters and stages
    Given User should be logged in with "<LoginRow>"
    And User is on the Deals page
    When the user clicks on the Pipeline button
    And applies Stage, Status and Type as filters and creates a pipeline of deals with "<PipelineName>"
    Then the pipeline should be created successfully with the applied filters
    And the user should see the correct group of deals in the pipeline view graph and the summary table based on the selected filters tab.
    Examples:
      | LoginRow | PipelineName |
        | 2        | Test Pipeline |