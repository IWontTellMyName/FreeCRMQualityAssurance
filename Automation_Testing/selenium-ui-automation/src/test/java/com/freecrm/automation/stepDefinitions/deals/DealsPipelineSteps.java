package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.deals.DealsPipelinePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DealsPipelineSteps {
    PageObjectManager  pageObjectManager;
    WebDriverManager  webDriverManager;
    DealsPipelinePage dealsPipelinePage;
    @When("the user clicks on the Pipeline button")
    public void the_user_clicks_on_the_pipeline_button() {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
        dealsPipelinePage = pageObjectManager.getDealsPipelinePage();
        dealsPipelinePage.clickPipelineButton();
    }
    @When("applies Stage, Status and Type as filters and creates a pipeline of deals with {string}")
    public void applies_stage_status_and_type_as_filters_and_creates_a_pipeline_of_deals(String title) throws InterruptedException {
        dealsPipelinePage.selectFieldDropdown();
        dealsPipelinePage.EnterPipelineName(title);
        dealsPipelinePage.clickCreateButton();
    }
    @Then("the pipeline should be created successfully with the applied filters")
    public void the_pipeline_should_be_created_successfully_with_the_applied_filters() throws InterruptedException {
        Assert.assertTrue(dealsPipelinePage.verifyPipelineCreation(), "Pipeline is not created successfully");
    }
    @Then("the user should see the correct group of deals in the pipeline view graph and the summary table based on the selected filters tab.")
    public void the_user_should_see_the_correct_group_of_deals_in_the_pipeline_view_graph_and_the_summary_table_based_on_the_selected_filters_tab() throws InterruptedException {
        Assert.assertTrue(dealsPipelinePage.verifyPipelineFilteringAndGrouping(), "Pipeline filtering and grouping is not working correctly");
    }

}
