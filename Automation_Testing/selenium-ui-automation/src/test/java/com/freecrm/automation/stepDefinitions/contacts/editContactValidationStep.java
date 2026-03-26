package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class editContactValidationStep {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();

    ContactPage contactPage;

    @When("user clicks on edit icon for {string}")
    public void user_clicks_on_edit_icon_for(String name) throws InterruptedException {
        contactPage = new ContactPage(driver);
        contactPage.clickEditContact(name);
        Thread.sleep(3000);
    }

    @When("user clears the last name field")
    public void user_clears_the_last_name_field() throws InterruptedException {
        Thread.sleep(2000);
        contactPage.clearLastName();
        contactPage.clickSaveButton();
    }

    @Then("error message for last name should be displayed")
    public void error_message_for_last_name_should_be_displayed() {
        boolean result = contactPage.isLastNameErrorDisplayed();
        Assert.assertTrue(result, "Last Name error not displayed");
    }
}
