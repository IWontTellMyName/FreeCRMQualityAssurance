package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class createContactStep {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();

    ContactPage contactPage;

    @When("user clicks on create button")
    public void user_clicks_on_create_button() {
        contactPage = new ContactPage(driver);
        contactPage.clickCreateButton();
    }

    @When("user enters contact details")
    public void user_enters_contact_details() {
        contactPage.enterFirstName("John");
        contactPage.enterLastName("Doe");
        contactPage.enterEmail("john@gmail.com");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        contactPage.clickSaveButton();
    }

    @Then("user should be navigated to new contact page")
    public void user_should_be_navigated_to_new_contact_page() {
        boolean result = contactPage.isContactCreated();
        Assert.assertTrue(result, "Contact creation failed");
    }
}
