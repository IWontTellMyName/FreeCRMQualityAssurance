package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteDealSteps {
    private DealsListPage dealsListPage;
    private WebDriver driver;
    PageObjectManager pageObjectManager;

    @Given("User is on the Deals page")
    public void user_is_on_the_deals_page() {
        WebDriverManager webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        DashboardPage dashboardPage = pageObjectManager.getDashboardPage();
        dealsListPage = pageObjectManager.getDealsListPage();
        dashboardPage.clickDealsIcon();
    }
    @When("the user selects a {string} deal from the list of deals and clicks on the delete icon")
    public void the_user_selects_a_deal_from_the_list_of_deals_and_clicks_on_the_delete_icon(String dealTitle) {
        driver.navigate().refresh();
        dealsListPage.clickTrashButton(dealTitle);
    }
    @When("cancels the delete action in the confirmation dialog")
    public void cancels_the_delete_action_in_the_confirmation_dialog() throws InterruptedException {
        dealsListPage.cancelDeletion();
    }
    @Then("the {string} should still be present in the list of deals")
    public void the_deal_should_still_be_present_in_the_list_of_deals(String dealTitle) throws InterruptedException {
        Assert.assertTrue(dealsListPage.validateDealPresence(dealTitle));
    }
    @When("the user selects the same {string} deal again and clicks on the delete icon")
    public void the_user_selects_the_same_deal_again_and_clicks_on_the_delete_icon(String dealTitle) {
        driver.navigate().refresh();
        dealsListPage.clickTrashButton(dealTitle);
    }
    @When("confirms the delete action in the confirmation dialog")
    public void confirms_the_delete_action_in_the_confirmation_dialog() throws InterruptedException {
        dealsListPage.confirmDeletion();
    }
    @Then("the {string} should be removed from the list of deals")
    public void the_deal_should_be_removed_from_the_list_of_deals(String dealTitle) throws InterruptedException {
        Assert.assertFalse(dealsListPage.validateDealPresence(dealTitle));
//        webDriverManager.closeDriver();
    }

}
