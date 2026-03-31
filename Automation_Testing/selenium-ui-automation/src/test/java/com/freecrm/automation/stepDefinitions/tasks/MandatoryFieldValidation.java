package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MandatoryFieldValidation {
    WebDriverManager webDriverManager;
    WebDriver driver;
    TasksPage tasksPage;
    PageObjectManager pageObjectManager;

    @When("the user leaves the task title empty")
    public void the_user_leaves_the_task_title_empty() {
        pageObjectManager = Hooks.getPageObjectManager();
        tasksPage = pageObjectManager.getTasksPage();
        tasksPage.clickSave();
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {

        String actualMessage = tasksPage.getTitleErrorMessage();

        Assert.assertEquals(actualMessage, expectedMessage,
                "Validation message mismatch!");
    }

    @Then("the task should not be created")
    public void the_task_should_not_be_created() {

        String currentUrl = Hooks.getDriver().getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/tasks/new"),
                "Task was created unexpectedly!");
    }
}