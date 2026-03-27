package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class createContactStep {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getDriver();

    ContactPage contactPage;

    @When("user clicks on create button")
    public void user_clicks_on_create_button() {
        contactPage = new ContactPage(driver);
        contactPage.clickCreateButton();
    }

    @When("user enters contact details using {string} and {string}")
    public void user_enters_contact_details(String RowNumber, String sheetName) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> loginInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), sheetName
        );

        int listIndex = Integer.parseInt(RowNumber) - 2;
        Map<String, String> contactInfo = loginInfo.get(listIndex);
        contactPage.enterFirstName(contactInfo.get("First Name"));
        contactPage.enterLastName(contactInfo.get("Last Name"));
        contactPage.enterEmail(contactInfo.get("Email"));
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
