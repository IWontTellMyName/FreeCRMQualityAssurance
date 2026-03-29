package com.freecrm.automation.stepDefinitions.company;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.company.ListViewPage;

public class BulkActionsAndAdminToolsSteps {
	WebDriverManager webDriverManager;
	WebDriver driver;
    PageObjectManager pageObjectManager;
    DashboardPage dashboardPage;
	ListViewPage listViewPage;

    @Given("User has navigated to the Companies dashboard")
    public void navigate_to_companies() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        dashboardPage = pageObjectManager.getDashboardPage();
        dashboardPage.clickCompanyIcon();

		listViewPage = pageObjectManager.getListViewPage();
		Assert.assertTrue(listViewPage.validateListViewPage());
    }

    // Locators based on provided HTML
    // By headerCheckbox = By.xpath("//thead//input[@type='checkbox']");
    // By rowCheckboxes = By.xpath("//tbody//input[@type='checkbox']");
    // By actionsDropdown = By.name("action");
    // By executeBtn = By.xpath("//div[contains(@class, 'checkmark icon')]/parent::div");

    //Scenario 1: Verify "Select All" functionality via header checkbox
    @When("User clicks the \"Select All\" checkbox in the table header")
    public void user_clicks_header_checkbox() throws InterruptedException {
        System.out.println("Bulk Actions And Admin Tools Scenario 1 starts");
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//thead//input[@type='checkbox']")).click();
    }

    @Then("all checkboxes in the company data grid rows should be checked")
    public void verify_all_selected() {
        for (WebElement cb : listViewPage.listItemCheckboxes) {
            Assert.assertTrue(cb.isSelected());
        }
    }

    //Scenario 2: Conditional visibility of the Actions dropdown
    @Given("no company records are currently selected")
    public void verify_none_selected(){
        System.out.println("Bulk Actions And Admin Tools Scenario 2 starts");
        for (WebElement cb : listViewPage.listItemCheckboxes) {
            if(cb.isSelected()){
                cb.click();
            }
        }
    }

    @Then("the \"Actions\" dropdown menu should not be visible")
    public void verify_dropdown_disabled() {
        Assert.assertFalse(driver.findElement(By.className("upward")).isDisplayed());
    }
    
    @When("User selects a single company record checkbox")
    public void select_one(){
        listViewPage.listItemCheckboxes.get(0).click();
    }
    
    @Then("the \"Actions\" dropdown menu should be visible")
    public void the_dropdown_menu_should_become_enabled() {
        Assert.assertTrue(driver.findElement(By.className("upward")).isDisplayed());
    }

    // @When("User selects multiple company records")
    // public void user_selects_multiple() {
    //     var checkboxes = driver.findElements(rowCheckboxes);
    //     checkboxes.get(0).click();
    //     checkboxes.get(1).click();
    // }

    // @And("User selects the {string} option from the Actions dropdown")
    // public void select_action_option(String optionText) {
    //     driver.findElement(actionsDropdown).click();
    //     driver.findElement(By.xpath("//span[text()='" + optionText + "']")).click();
    // }

    // @And("User clicks the execution checkmark button")
    // public void click_execute() {
    //     driver.findElement(executeBtn).click();
    // }

    // @Then("the selected records should be removed from the data grid")
    // public void verify_records_removed() {
    //     // Verification logic: ensure 'Capgemini' or 'Google' (from your HTML) are gone
    //     boolean isPresent = driver.findElements(By.linkText("Capgemini")).size() > 0;
    //     Assert.assertFalse(isPresent);
    // }
}