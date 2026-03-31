package com.freecrm.automation.stepDefinitions.company;

import io.cucumber.java.en.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.testng.Assert;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.company.ListViewPage;

public class DataIntegrityAndGridInteractionsSteps {
	WebDriver driver;
    PageObjectManager pageObjectManager;
    DashboardPage dashboardPage;
	ListViewPage listViewPage;
    String companyName;

    

	@Given("User has navigated to the Company dashboard")
    public void navigate_to_companies() {
        driver = Hooks.getDriver();
        pageObjectManager = Hooks.getPageObjectManager();
        dashboardPage = pageObjectManager.getDashboardPage();
        dashboardPage.clickCompanyIcon();

		listViewPage = pageObjectManager.getListViewPage();
		Assert.assertTrue(listViewPage.validateListViewPage());







    }

    // Locators mapped to provided HTML
    By recordCountLabel = By.cssSelector(".ui.blue.basic.horizontal.label");
    // By companyTableRows = By.xpath("//table[contains(@class, 'custom-grid')]/tbody/tr");

	//Scenario 1: Validate address concatenation for company records
    @Then("the address for {string} should display as {string}")
    public void verify_address_concatenation(String rowNum, String expectedAddress) throws IOException {
        
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> companyInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), "Company"
        );

        int listIndex = Integer.parseInt(rowNum) - 2;
        Map<String, String> company = companyInfo.get(listIndex);
        String companyName = company.get("Company");

        
        System.out.println("Data Integrity and Grid Interactions Scenario 1 starts");
        // Find row where 2nd column matches company name, then get text from 3rd column
        String xpath = "//td/a[text()='" + companyName + "']/parent::td/following-sibling::td[1]";
        String actualAddress = driver.findElement(By.xpath(xpath)).getText();
        Assert.assertEquals(actualAddress, expectedAddress);
    }
	
	//Scenario 2: Verify navigation via company name hyperlink
	@When("User clicks on the company name {string}")
	public void click_company_link(String rowNum) throws InterruptedException, IOException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> companyInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), "Company"
        );

        int listIndex = Integer.parseInt(rowNum) - 2;
        Map<String, String> company = companyInfo.get(listIndex);
        String companyName = company.get("Company");

		System.out.println("Data Integrity and Grid Interactions Scenario 2 starts");
        driver.navigate().refresh();
        // Thread.sleep(5000);
        driver.findElement(By.linkText(companyName)).click();
    }

    @Then("the user should be redirected to the detailed record page for {string}")
    public void verify_url_id(String rowNum) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> companyInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), "Company"
        );

        int listIndex = Integer.parseInt(rowNum) - 2;
        Map<String, String> company = companyInfo.get(listIndex);
        String companyName = company.get("Company");

		String displayText = driver.findElement(By.className("selectable")).getText();
        Assert.assertEquals(displayText, companyName);
    }

    // @Given("the current record count label displays {string}")
    // public void check_initial_count(String expectedText) {
    //     String actualText = driver.findElement(recordCountLabel).getText();
    //     Assert.assertEquals(actualText, expectedText);
    // }

    // @When("User clicks the \"Delete\" icon for the {string} record")
    // public void click_delete_icon(String companyName) {
    //     // Find the trash icon button in the same row as the company name
    //     String xpath = "//td/a[text()='" + companyName + "']/parent::td/following-sibling::td/button[i[@class='trash icon']]";
    //     driver.findElement(By.xpath(xpath)).click();
    // }

    // @Then("the record count label should update to {string}")
    // public void verify_updated_count(String expectedCount) {
    //     // Wait for dynamic update (simplified for this example)
    //     String actualCount = driver.findElement(recordCountLabel).getText();
    //     Assert.assertEquals(actualCount, expectedCount);
    // }
}