package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class verifyContactsStep {
    WebDriverManager webDriverManager;
    DashboardPage dashboardPage;
    ContactPage contactPage;
    WebDriver driver;
    @Given("the user navigates to the contacts section")
    public void the_user_navigates_to_the_contacts_section() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickContactsIcon();
    }
    @Then("the contacts list should be displayed on the contacts page")
    public void the_contacts_list_should_be_displayed_on_the_contacts_page() {
        contactPage = new ContactPage(driver);
        boolean result = contactPage.validateContactsPage();
        Assert.assertTrue(result);
    }

}
