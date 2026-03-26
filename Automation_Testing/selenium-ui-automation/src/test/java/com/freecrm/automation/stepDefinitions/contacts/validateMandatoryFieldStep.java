package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
public class validateMandatoryFieldStep {
    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();

    ContactPage contactPage;

    @When("user clicks on create button and user clicks on save button without entering mandatory fields")
    public void user_clicks_on_save_button_without_entering_mandatory_fields() {
        contactPage = new ContactPage(driver);
        contactPage.clickCreateButton();
        contactPage.clickSaveButton();
    }

    @Then("error message for first name should be displayed")
    public void error_message_for_first_name_should_be_displayed() {
        boolean result = contactPage.isFirstNameErrorDisplayed();
        Assert.assertTrue(result, "First Name error not displayed");
    }

//    @Then("error message for last name should be displayed")
//    public void error_message_for_last_name_should_be_displayed() {
//        boolean result = contactPage.isLastNameErrorDisplayed();
//        Assert.assertTrue(result, "Last Name error not displayed");
//    }
}
