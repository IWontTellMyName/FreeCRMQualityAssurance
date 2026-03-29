package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchAndFilterDealsSteps {
    private WebDriverManager  webDriverManager;
    private DealsListPage dealsListPage;
    private WebDriver driver;
    private PageObjectManager pageObjectManager;
    @When("the user clicks on the Deals tab in the main navigation menu")
    public void the_user_clicks_on_the_deals_tab_in_the_main_navigation_menu()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        DashboardPage dashboardPage = pageObjectManager.getDashboardPage();
        dashboardPage.clickDealsIcon();
        dealsListPage =pageObjectManager.getDealsListPage();
    }
    @Then("the user should be on the Deals page")
    public void the_user_should_be_on_the_deals_page() {
        Assert.assertTrue(dealsListPage.isDealsListDisplayed());
    }
    @When("the user enters {string} in the global search box and clicks the search icon")
    public void the_user_enters_in_the_global_search_box_and_clicks_the_search_icon(String searchTerm) {
       dealsListPage.performGlobalSearch(searchTerm);
    }
    @Then("the user should see a list of Records matching the search term")
    public void the_user_should_see_a_list_of_records_matching_the_search_term() {
        Assert.assertTrue(dealsListPage.validateDealsRecord());
    }
    @When("the user clicks on Deals in the Records")
    public void the_user_clicks_on_deals_in_the_records(){
        dealsListPage.clickDealsRecord();
    }
    @Then("the user should see a list of Deals matching the {string}")
    public void the_user_should_see_a_list_of_deals_matching_the_search_term(String searchTerm) throws InterruptedException {
        Assert.assertTrue(dealsListPage.validateGlobalSearch(searchTerm));
        Thread.sleep(3000);
        driver.navigate().back();
    }

    @When("the user clicks on the show filter button and selects Stage from the dropdown")
    public void the_user_clicks_on_the_show_filter_button_and_selects_from_the_dropdown() {
        webDriverManager= new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        dealsListPage = pageObjectManager.getDealsListPage();
        dealsListPage.clickShowFiltersButton();
        dealsListPage.displayFilterDropdown();
        dealsListPage.selectStageFilter();
    }
    @When("the user selects {string} from the Stage options and applies the filter")
    public void the_user_selects_from_the_stage_options_and_applies_the_filter(String stage) {
        dealsListPage.selectQualifyStage(stage);
        dealsListPage.clickApplyFilterButton();
    }
    @Then("the user should see a list of Deals that are in the {string} stage")
    public void the_user_should_see_a_list_of_deals_that_are_in_the_stage(String string) throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(dealsListPage.validateFilterResults());
    }

    @When("the user clicks on the show filter button and selects Title from the dropdown")
    public void the_user_clicks_on_the_show_filter_button_and_selects_title_from_the_dropdown() throws InterruptedException {
        webDriverManager= new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        dealsListPage = pageObjectManager.getDealsListPage();
        try {
            dealsListPage.clickClearFilterButton();
        }
        catch (Exception e)
        {
            System.out.println("No filters to clear");
        }
        Thread.sleep(2000);
        dealsListPage.clickShowFiltersButton();
        Thread.sleep(2000);
        dealsListPage.displayFilterDropdown();
        Thread.sleep(2000);
        dealsListPage.selectTitleFilter();
    }
    @When("the user enters {string} in the Title filter and applies the filter")
    public void the_user_enters_in_the_title_filter_and_applies_the_filter(String dealTitle) throws InterruptedException {
        dealsListPage.enterTitle(dealTitle + " " + ConfigFileReader.getInstance().getBrowser());
        Thread.sleep(1000);
        dealsListPage.clickApplyFilterButton();
    }
    @Then("the user should see an empty list of Deals, indicating no matches found")
    public void the_user_should_see_an_empty_list_of_deals_indicating_no_matches_found() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(dealsListPage.validateNoRecords());
    }


}
