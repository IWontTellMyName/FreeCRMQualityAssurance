package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class searchContactStep {


    PageObjectManager pageObjectManager = Hooks.getPageObjectManager();

    ContactPage contactPage;

    @When("user searches for {string}")
    public void user_searches_for(String name) {
        contactPage = pageObjectManager.getContactPage();
        contactPage.searchContact(name);
    }

    @Then("correct contact {string} should be displayed")
    public void correct_contact_should_be_displayed(String name) {
        boolean result = contactPage.isSearchResultDisplayed(name);
        Assert.assertTrue(result, "Search result not displayed correctly");
    }
}
