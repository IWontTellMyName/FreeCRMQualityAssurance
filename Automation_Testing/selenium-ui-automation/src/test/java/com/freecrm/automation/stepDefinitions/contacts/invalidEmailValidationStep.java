package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class invalidEmailValidationStep {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();
    PageObjectManager pageObjectManager = new PageObjectManager(driver);

    ContactPage contactPage;

    @When("user enters invalid email {string}")
    public void user_enters_invalid_email(String email) {
        contactPage = pageObjectManager.getContactPage();
        contactPage.enterEmail(email);   // overwrite valid email
    }

    @When("user enters first name {string}")
    public void user_enters_first_name(String fname) {
        contactPage = pageObjectManager.getContactPage();
        contactPage.enterFirstName(fname);
    }

    @When("user enters last name {string}")
    public void user_enters_last_name(String lname) {
        contactPage.enterLastName(lname);
    }

    @Then("contact should be created successfully with invalid email")
    public void contact_should_be_created_successfully_with_invalid_email() {
        boolean result = contactPage.isContactCreated();
        Assert.assertTrue(result, "Contact not created with invalid email");
    }
}
