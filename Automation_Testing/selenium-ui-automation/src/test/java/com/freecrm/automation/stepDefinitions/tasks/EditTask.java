package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class EditTask {

    WebDriverManager webDriverManager;
    TasksPage tasksPage;

    @Given("the user selects an existing task")
    public void the_user_selects_an_existing_task() throws InterruptedException {
        webDriverManager = new WebDriverManager();
        tasksPage = new TasksPage(webDriverManager.getDriver());
        tasksPage.clickTaskByName("task");
    }

    @Then("the task details page should be displayed")
    public void the_task_details_page_should_be_displayed() {
        Assert.assertTrue(
                webDriverManager.getDriver().getCurrentUrl().contains("/tasks/"),
                "Not on task details page"
        );
    }

    @When("the user clicks on Edit")
    public void the_user_clicks_on_edit() {
        tasksPage.clickEditButton();
    }

    @When("modifies the task title to {string}")
    public void modifies_the_task_title_to(String newTitle) {
        tasksPage.enterTitle(newTitle);
    }

    @When("clicks on Save after editing")
    public void clicks_on_save_after_editing() {
        tasksPage.clickSave();
    }

    @Then("the task should be updated successfully")
    public void the_task_should_be_updated_successfully() {
        Assert.assertTrue(
                webDriverManager.getDriver().getCurrentUrl().contains("/tasks/"),
                "Update failed"
        );
    }

    @Then("the updated title {string} should be displayed")
    public void the_updated_title_should_be_displayed(String expectedTitle) {
        String actualTitle = tasksPage.getDisplayedTaskTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}