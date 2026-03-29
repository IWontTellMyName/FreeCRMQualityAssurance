package com.freecrm.automation.stepDefinitions.deals;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.pageObjects.deals.DealDetailsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class DealIntegrationTestingSteps {
    DealDetailsPage dealDetailsPage;
    PageObjectManager pageObjectManager;

    @When("the user opens the deal {string}")
    public void the_user_opens_the_deal(String dealTitle) throws InterruptedException {
        pageObjectManager = Hooks.getPageObjectManager();
        dealDetailsPage = pageObjectManager.getDealDetailsPage();
        dealDetailsPage.openDealDetailsPage(dealTitle);
    }

    @When("edits the deal")
    public void edits_the_deal() {
        dealDetailsPage.clickEditIcon();
    }

    @When("associates contact, company, and product using {string} from {string}")
    public void associates_contact_company_and_product_using_from(String rowNumber, String sheetName) throws InterruptedException, IOException {
        dealDetailsPage.editDeal(rowNumber, sheetName);
    }

    @And("clicks save")
    public void clicks_save() throws InterruptedException {
        dealDetailsPage.clickSave();
    }

    @When("associates event using {string} from {string}")
    public void associates_event_using_from(String string, String string2) {
        dealDetailsPage.addEvent();
    }

    @Then("the deal should be updated successfully with the associated contact, company, product, task and event")
    public void the_deal_should_be_updated_successfully_with_the_associated_contact_company_product_task_and_event() throws InterruptedException {
        Assert.assertTrue(dealDetailsPage.verifyDealEdit(), "Deal not updated successfully");
    }

    @Then("the event should be visible in the calendar")
    public void the_event_should_be_visible_in_the_calendar() throws InterruptedException {
        Assert.assertTrue(dealDetailsPage.openCalender(), "Event not visible in the calendar");
    }
}
