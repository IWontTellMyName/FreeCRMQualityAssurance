package com.freecrm.automation.stepDefinitions.company;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.company.ListViewPage;

public class SearchAndFilterSteps {
	WebDriverManager webDriverManager;
	WebDriver driver;
    PageObjectManager pageObjectManager;
    DashboardPage dashboardPage;
	ListViewPage listViewPage;

    @Given("User has navigated to Companies dashboard")
    public void navigate_to_companies() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        dashboardPage = pageObjectManager.getDashboardPage();
        dashboardPage.clickCompanyIcon();

		listViewPage = pageObjectManager.getListViewPage();
		Assert.assertTrue(listViewPage.validateListViewPage());
    }

    // // Scenario 1: Successful keyword search
    // @When("User enters {string} in the global search input field")
    // public void user_enters_search_term(String companyName) {
    //     WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search']"));
    //     searchInput.sendKeys(companyName, Keys.ENTER);
    // }

    // @Then("the data grid should filter to display the {string} record")
    // public void verify_filtered_record(String expectedName) {
    //     WebElement firstRowName = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/a"));
    //     Assert.assertEquals(firstRowName.getText(), expectedName);
    // }

    // @Then("the record count label should update to {string}")
    // public void verify_record_count(String expectedCountText) {
    //     WebElement countLabel = driver.findElement(By.cssSelector(".ui.blue.basic.horizontal.label"));
    //     Assert.assertEquals(countLabel.getText(), expectedCountText);
    // }

    // Scenario 2: Toggle filters
    @When("User clicks on the \"Show Filters\" button in the toolbar")
    public void user_clicks_button() {
        System.out.println("Search & Filter Scenario 2 started");
		listViewPage.filterToggle.click();
    }

    @Then("the hidden filtering panel should expand above the data grid")
    public void verify_filter_panel_visible() {
        // Logic to check if the filter div (usually dynamic) is displayed
        boolean isFilterVisible = driver.findElement(By.className("form")).isDisplayed();
        Assert.assertTrue(isFilterVisible);
    }

	@Then("the button text should change to {string}")
	public void verify_hide_button(String buttonText){
		Assert.assertEquals(listViewPage.filterToggle.getText(), buttonText);
	}

    // // Scenario 3: Reset search
    // @When("User selects the {string} option from the View dropdown menu")
    // public void user_selects_dropdown_option(String option) {
    //     driver.findElement(By.name("view")).click();
    //     driver.findElement(By.xpath("//span[text()='" + option + "']")).click();
    // }

    // // Scenario 4: Negative Search
    // @Then("the data grid should be empty")
    // public void verify_empty_grid() {
    //     int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();
    //     Assert.assertTrue(rows == 0 || driver.findElement(By.xpath("//td[text()='No results found']")).isDisplayed());
    // }





    // By headerCheckbox = By.xpath("//thead//input[@type='checkbox']");
    // By rowCheckboxes = By.xpath("//tbody//input[@type='checkbox']");
    // By actionsDropdown = By.name("action");
    // By executeBtn = By.xpath("//div[contains(@class, 'checkmark icon')]/parent::div");

    // @When("User clicks the \"Select All\" checkbox in the table header")
    // public void user_clicks_header_checkbox() throws InterruptedException {
    //     driver.navigate().refresh();
    //     Thread.sleep(2000);
    //     driver.findElement(headerCheckbox).click();
    // }

    // @Then("all checkboxes in the company data grid rows should be checked")
    // public void verify_all_checked() {
    //     // var checkboxes = driver.findElements(rowCheckboxes);
    //     System.out.println(driver.findElements(rowCheckboxes).size());
    //     for (WebElement cb : listViewPage.listItemCheckboxes) {
    //         // In Semantic UI, we check the hidden input or the parent div's class
    //         Assert.assertTrue(cb.isSelected());
    //     }
    // }

    // @Then("the \"Actions\" dropdown menu should become enabled")
    // public void the_dropdown_menu_should_become_enabled() {
    //     Assert.assertTrue(driver.findElement(By.name("action")).isDisplayed());
    // }
    
    // @Then("the \"Actions\" dropdown menu should be in a disabled state")
    // public void verify_dropdown_disabled() {
    //     WebElement dropdown = driver.findElement(actionsDropdown);
    //     // Checking Semantic UI 'disabled' class
    //     Assert.assertTrue(dropdown.getAttribute("class").contains("disabled"));
    // }
}