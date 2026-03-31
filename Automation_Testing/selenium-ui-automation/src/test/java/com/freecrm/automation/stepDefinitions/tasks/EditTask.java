package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.hooks.Hooks;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EditTask {
    WebDriverManager webDriverManager;
    WebDriver driver;
    TasksPage tasksPage;
    PageObjectManager pageObjectManager;

    @Given("the user selects an existing task")
    public void the_user_selects_an_existing_task() throws InterruptedException {
        pageObjectManager = Hooks.getPageObjectManager();
        tasksPage = pageObjectManager.getTasksPage();
        tasksPage.clickTaskByName("Task");
    }

    @Then("the task details page should be displayed")
    public void the_task_details_page_should_be_displayed() {
        Assert.assertTrue(
                Hooks.getDriver().getCurrentUrl().contains("/tasks/"),
                "Not on task details page"
        );
    }

    @When("the user clicks on Edit")
    public void the_user_clicks_on_edit() {
        tasksPage.clickEditButton();
    }

    @When("modifies the task title to {string}")
    public void modifies_the_task_title_to(String newTitle) throws InterruptedException {
        tasksPage.enterTitle(newTitle);
    }

    @When("clicks on Save after editing")
    public void clicks_on_save_after_editing() {
        tasksPage.clickSave();
    }

    @Then("the task should be updated successfully")
    public void the_task_should_be_updated_successfully() {
        Assert.assertTrue(
                Hooks.getDriver().getCurrentUrl().contains("/tasks/"),
                "Update failed"
        );
    }

    @Then("the updated title {string} should be displayed")
    public void the_updated_title_should_be_displayed(String expectedTitle) {
        String actualTitle = tasksPage.getDisplayedTaskTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}