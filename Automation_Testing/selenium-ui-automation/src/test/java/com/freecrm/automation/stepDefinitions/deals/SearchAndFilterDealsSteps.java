package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchAndFilterDealsSteps {
    private DashboardPage dashboardPage;
    private WebDriverManager  webDriverManager;
    private DealsListPage dealsListPage;
    private WebDriver driver;
    @When("the user clicks on the Deals tab in the main navigation menu")
    public void the_user_clicks_on_the_deals_tab_in_the_main_navigation_menu()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        dashboardPage = new DashboardPage(driver);

    }
    @Then("the user should be on the Deals page")
    public void the_user_should_be_on_the_deals_page() {
        dealsListPage =  new DealsListPage(driver);
        Assert.assertTrue(dealsListPage.isDealsListDisplayed());
    }
    @When("the user enters {string} in the global search box and clicks the search icon")
    public void the_user_enters_in_the_global_search_box_and_clicks_the_search_icon(String searchTerm) {
       dealsListPage.performGlobalSearch(searchTerm);
    }
    @Then("the user should see a list of Records matching the search term")
    public void the_user_should_see_a_list_of_records_matching_the_search_term() {
        dealsListPage.validateDealsRecord();
    }
    @When("the user clicks on Deals in the Records")
    public void the_user_clicks_on_deals_in_the_records(){
        dealsListPage.clickDealsRecord();
    }
    @Then("the user should see a list of Deals matching the {string}")
    public void the_user_should_see_a_list_of_deals_matching_the_search_term(String searchTerm) throws InterruptedException {
        Assert.assertTrue(dealsListPage.validateGlobalSearch(searchTerm));
        Thread.sleep(3000);

    }

    @When("the user clicks on the show filter button and selects {string} from the dropdown")
    public void the_user_clicks_on_the_show_filter_button_and_selects_from_the_dropdown(String string) {
        dealsListPage.clickShowFiltersButton();
        dealsListPage.displayFilterDropdown();
        dealsListPage.selectStageFilter();
    }
    @When("the user selects {string} from the Stage options and applies the filter")
    public void the_user_selects_from_the_stage_options_and_applies_the_filter(String string) {
        dealsListPage.selectQualifyStage();
        dealsListPage.clickApplyFilterButton();
    }
    @Then("the user should see a list of Deals that are in the {string} stage")
    public void the_user_should_see_a_list_of_deals_that_are_in_the_stage(String string) {
        Assert.assertTrue(dealsListPage.validateFilterResults());
    }


}
