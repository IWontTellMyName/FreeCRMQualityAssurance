package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class filterContactStep {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();

    ContactPage contactPage;

    @When("user clicks on show filters button")
    public void user_clicks_on_show_filters_button() {
        contactPage = new ContactPage(driver);
        contactPage.clickShowFilters();
    }

    @When("user clicks on search dropdown")
    public void user_clicks_on_search_dropdown() {
        contactPage.clickSearchDropdown();
    }

    @When("user selects {string} from dropdown")
    public void user_selects_from_dropdown(String field) {
        contactPage.selectFilterField(field);
    }

    @When("user enters value {string}")
    public void user_enters_value(String value) {
        contactPage.enterFilterValue(value);
    }

    @When("user clicks on filter button")
    public void user_clicks_on_filter_button() {
        contactPage.clickFilterButton();
    }

    @Then("filtered result should display contact {string}")
    public void filtered_result_should_display_contact(String name) {
        boolean result = contactPage.isFilteredResultDisplayed(name);
        Assert.assertTrue(result, "Filtered contact not found");
    }
}
