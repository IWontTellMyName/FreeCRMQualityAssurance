package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DueDateValidation {
    WebDriverManager webDriverManager;
    WebDriver driver;
    TasksPage tasksPage;
    PageObjectManager pageObjectManager;

    @When("the user enters task title {string}")
    public void the_user_enters_task_title(String string) {
        pageObjectManager = Hooks.getPageObjectManager();
        tasksPage = pageObjectManager.getTasksPage();
        tasksPage.enter_title(string);
    }

    @And("the user selects due date {string} as a past date")
    public void the_user_selects_due_date_as_a_past_date(String date) {
        tasksPage.enterDueDate(date);
    }

    @Then("no validation error message should be shown for past date")
    public void no_validation_error_message_should_be_displayed() {

        String errorMessage = tasksPage.getDateValidationMessage();

        Assert.assertTrue(errorMessage == null || errorMessage.trim().isEmpty(),
                "Validation message was displayed unexpectedly!");
//        Assert.assertNotNull(errorMessage, "Validation message is null!");
//        Assert.assertFalse(errorMessage.trim().length() < 0,
//                "Validation message not displayed!");
    }

}