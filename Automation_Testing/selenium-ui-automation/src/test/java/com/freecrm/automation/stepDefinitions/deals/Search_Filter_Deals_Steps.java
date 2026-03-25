package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search_Filter_Deals_Steps {
    DashboardPage dashboardPage;
    WebDriverManager  webDriverManager;
    @When("the user clicks on the Deals tab in the main navigation menu")
    public void the_user_clicks_on_the_deals_tab_in_the_main_navigation_menu()
    {
        webDriverManager = new WebDriverManager();
    dashboardPage = new DashboardPage(webDriverManager.getDriver());
    dashboardPage.clickDealsIcon();
    }
    @Then("the user should be on the Deals page")
    public void the_user_should_be_on_the_deals_page() {

    }
    @When("the user enters {string} in the global search box and clicks the search icon")
    public void the_user_enters_in_the_global_search_box_and_clicks_the_search_icon(String string) {

    }
    @Then("the user should see a list of Records matching the search term")
    public void the_user_should_see_a_list_of_records_matching_the_search_term() {

    }
    @When("the user clicks on Deals in the Records")
    public void the_user_clicks_on_deals_in_the_records() {

    }
    @Then("the user should see a list of Deals matching the search term")
    public void the_user_should_see_a_list_of_deals_matching_the_search_term() {

    }
}
