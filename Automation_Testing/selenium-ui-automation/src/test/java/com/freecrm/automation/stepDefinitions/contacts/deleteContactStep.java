package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class deleteContactStep {


    PageObjectManager pageObjectManager = Hooks.getPageObjectManager();

    ContactPage contactPage;

    @When("user deletes contact {string}")
    public void user_deletes_contact(String name) {
        contactPage = pageObjectManager.getContactPage();

//        contactPage.selectContact(name);
        contactPage.clickDeleteContact(name);
        contactPage.confirmDelete();
    }

    @Then("contact {string} should be deleted")
    public void contact_should_be_deleted(String name) {
        boolean result = contactPage.isContactDeleted(name);
        Assert.assertTrue(result, "Contact was not deleted");
    }
}
