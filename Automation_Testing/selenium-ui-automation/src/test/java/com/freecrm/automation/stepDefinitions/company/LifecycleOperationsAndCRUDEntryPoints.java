package com.freecrm.automation.stepDefinitions.company;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.company.ListViewPage;

public class LifecycleOperationsAndCRUDEntryPoints {
	WebDriverManager webDriverManager;
    DashboardPage dashboardPage;
	ListViewPage listViewPage;
	WebDriver driver;

	@Given("User has navigated to Company dashboard")
    public void navigate_to_companies() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickCompanyIcon();

		listViewPage = new ListViewPage(driver);
		Assert.assertTrue(listViewPage.validateListViewPage());
    }

    // Locators based on HTML structure
    // By createBtn = By.xpath("//button[contains(text(), 'Create')]");
    // By cancelBtn = By.xpath("//button[text()='Cancel']");
    // By recordCountLabel = By.cssSelector(".ui.blue.basic.horizontal.label");

    //Scenario 1: Navigate to the New Company creation form
	@When("User clicks on the \"Create\" button in the toolbar")
    public void click_create_button() {
        System.out.println("Lifecycle Operations and CRUD Entry Points Scenario 1 starts");
        listViewPage.createButton.click();
    }

    @Then("the page URL should contain {string}")
    public void verify_url(String url) {
		Assert.assertTrue(driver.getCurrentUrl().contains(url));
    }

    //Scenario 2: Navigate to the Edit interface for an existing record
	@When("User clicks the inline \"Edit\" icon for a record")
    public void click_inline_edit() {
		System.out.println("Lifecycle Operations and CRUD Entry Points Scenario 2 starts");
        // Find row by company name and click the button containing the edit icon
        String xpath = "(//*[@class='edit icon'])[2]";
        driver.findElement(By.xpath(xpath)).click();
    }

	//Scenario 3:Access the read-only view of a company record
    @When("User clicks the inline \"View\" icon for the {string} record")
    public void click_inline_view(String companyName) {
        System.out.println("Lifecycle Operations and CRUD Entry Points Scenario 3 starts");
        // 'unhide icon' is the Semantic UI class for the 'Eye' view icon in this HTML
        String xpath = "//a[text()='" + companyName + "']/ancestor::tr//i[@class='unhide icon']/parent::button";
        driver.findElement(By.xpath(xpath)).click();
    }

	@Then("the non-editable record summary details page for {string} should open")
	public void check_summary(String companyName) {
		String displayText = driver.findElement(By.className("selectable")).getText();
        Assert.assertEquals(displayText, companyName);
	}

//     @Then("the user should be returned to the Companies data grid")
//     public void verify_return_to_grid() {
//         String currentUrl = driver.getCurrentUrl();
//         Assert.assertTrue(currentUrl.endsWith("/companies"));
//     }

//     @And("the grid should display {string}")
//     public void verify_grid_count(String expectedText) {
//         String actualText = driver.findElement(recordCountLabel).getText();
//         Assert.assertEquals(actualText, expectedText);
//     }
	
}
