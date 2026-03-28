package com.freecrm.automation.stepDefinitions.tasks;

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
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        tasksPage = pageObjectManager.getTasksPage();
        tasksPage.enter_title(string);
    }

    @And("the user selects due date {string} as a past date")
    public void the_user_selects_due_date_as_a_past_date(String date) {
        tasksPage.enterDueDate(date);
    }

    @Then("a validation error message should be displayed")
    public void a_validation_error_message_should_be_displayed() {

        String errorMessage = tasksPage.getDateValidationMessage();

        Assert.assertNotNull(errorMessage, "Validation message is null!");
        Assert.assertFalse(errorMessage.trim().length() < 0,
                "Validation message not displayed!");
    }

    @Then("the task should not be created by due date")
    public void task_should_not_be_created_by_due_date() {

        boolean taskCreated = tasksPage.isTaskCreated();

        Assert.assertFalse(taskCreated, "Task was created unexpectedly!");
    }
}