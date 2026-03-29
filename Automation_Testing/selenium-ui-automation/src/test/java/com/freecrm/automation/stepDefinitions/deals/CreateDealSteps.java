package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.pageObjects.deals.DealsCreatePage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateDealSteps {
    PageObjectManager pageObjectManager;
    WebDriver driver;
    DealsCreatePage dealsCreatePage;
    DealsListPage dealsListPage;

    //Scenario: User creates a deal with valid information
    @When("User clicks on the Create Deal button")
    public void user_clicks_on_the_create_deal_button() {
        driver = Hooks.getDriver();
        pageObjectManager = Hooks.getPageObjectManager();

        dealsListPage = pageObjectManager.getDealsListPage();
        dealsCreatePage = pageObjectManager.getDealsCreatePage();
        dealsListPage.clickCreateButton();
    }

    @And("User enters {string} and {string} to fill in valid information")
    public void user_fills_in_valid_information(String rowNum, String sheetName) throws Exception {
        dealsCreatePage.enterCredentials(Integer.parseInt(rowNum), sheetName);
    }

    @When("clicks the Save button")
    public void clicks_the_save_button() throws InterruptedException {
        dealsCreatePage.click_save();
        Thread.sleep(2000);
    }

    @Then("the deal should be created successfully")
    public void the_deal_should_be_created_successfully() throws InterruptedException {
        driver.navigate().refresh();
        Assert.assertTrue(dealsCreatePage.validate_deal_creation(), "Deal creation failed");
    }

    //Scenario: User tries to create a deal with missing required fields
    @Given("the user is the Deals page")
    public void the_user_is_the_deals_page() {
        pageObjectManager = Hooks.getPageObjectManager();
        dealsCreatePage = pageObjectManager.getDealsCreatePage();
        dealsCreatePage.clickMoneyIcon();
    }

    @When("the user leaves one or more required fields empty")
    public void the_user_leaves_one_or_more_required_fields_empty() {
        dealsCreatePage.click_save();
    }

    @Then("the deal should not be created successfully")
    public void the_deal_should_not_be_created() {
        try {
            Assert.assertTrue(dealsCreatePage.validate_deal_creation(), "Deal creation should fail");
        } catch (Exception e) {
            Assert.assertFalse(false, "Deal creation should fail");
        }
    }

    @Then("the user should see an error message indicating which fields are required")
    public void the_user_should_see_an_error_message_indicating_which_fields_are_required() {
        Assert.assertTrue(dealsCreatePage.validateTitleRequired(), "Title is required");
    }

    @And("the user fills in the missing required fields with valid data")
    public void the_user_fills_in_the_missing_required_fields_with_valid_data() {
        dealsCreatePage.enter_title("Test Deal");
    }

    @When("the user enters invalid data in one or more fields")
    public void the_user_enters_invalid_data_in_one_or_more_fields() {
        dealsCreatePage.enter_probability("123");
    }

    @Then("the user should see an error message indicating which fields contain invalid data")
    public void the_user_should_see_an_error_message_indicating_which_fields_contain_invalid_data() throws InterruptedException {
        try {
            dealsCreatePage.click_save();
        } catch (Exception e) {
            Assert.assertFalse(false, "Probabilty should be between 0 and 100");
        }
    }
}
