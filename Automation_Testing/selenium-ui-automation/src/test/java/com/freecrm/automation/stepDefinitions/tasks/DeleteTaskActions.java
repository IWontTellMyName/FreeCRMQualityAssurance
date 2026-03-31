package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteTaskActions {
    WebDriverManager webDriverManager;
    WebDriver driver;
    TasksPage tasksPage;
    PageObjectManager pageObjectManager;
    String taskName = "Task"; // keep simple for now

    @When("the user clicks on Delete")
    public void the_user_clicks_on_delete() {
        pageObjectManager = Hooks.getPageObjectManager();

        tasksPage = pageObjectManager.getTasksPage();
        tasksPage.clickDeleteForTask(taskName);
    }

    @When("the user clicks on Cancel delete")
    public void the_user_clicks_on_cancel_delete() {

        tasksPage.clickCancelDelete();
    }

    @Then("the task should still exist")
    public void the_task_should_still_exist() {

        boolean isPresent = tasksPage.isTaskPresent(taskName);

        Assert.assertTrue(isPresent, "Task was deleted but should remain!");
    }
}