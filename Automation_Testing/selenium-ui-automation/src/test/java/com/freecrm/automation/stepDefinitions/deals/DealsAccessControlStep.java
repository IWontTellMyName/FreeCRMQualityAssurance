package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.deals.DealsCreatePage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DealsAccessControlStep {
    WebDriverManager webDriverManager;
    PageObjectManager pageObjectManager;
    DealsCreatePage dealsCreatePage;
    DealsListPage dealsListPage;

    @When("sets Access to Private")
    public void sets_access_to() throws InterruptedException {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
        dealsListPage = pageObjectManager.getDealsListPage();
        dealsCreatePage = pageObjectManager.getDealsCreatePage();
        dealsCreatePage.clickUnlockIcon();
        Thread.sleep(2000);
    }

    @When("adds user {string} in Select users allowed access")
    public void adds_user_in(String rowNumber) throws Exception {
        dealsCreatePage.selectUsersAllowedAccess(rowNumber);

    }

    @Then("User logs out")
    public void user_logs_out() throws InterruptedException {
        dealsCreatePage.logout();
        Thread.sleep(2000);
    }

    @Then("the {string} should be visible in the results")
    public void the_deal_should_be_visible_in_the_results(String title) throws InterruptedException {
        Assert.assertTrue(dealsListPage.validateDealPresence(title), "Deal is not visible in the results");
    }

    @Then("the {string} should not be visible in the results")
    public void the_deal_should_not_be_visible_in_the_results(String title) throws InterruptedException {
        Assert.assertTrue(dealsListPage.validateNoRecords(), "Deal is visible in the results");
    }
}
