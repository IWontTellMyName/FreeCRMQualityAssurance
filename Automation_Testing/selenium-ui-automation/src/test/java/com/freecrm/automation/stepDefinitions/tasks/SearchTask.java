package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
        import org.testng.Assert;

public class SearchTask {

    WebDriverManager webDriverManager;
    TasksPage tasksPage;

    @When("the user searches for task {string}")
    public void the_user_searches_for_task(String keyword) {
        webDriverManager = new WebDriverManager();
        tasksPage = new TasksPage(webDriverManager.getDriver());

        tasksPage.searchTask(keyword);
    }

    @Then("relevant task results should be displayed")
    public void relevant_task_results_should_be_displayed() {

        boolean isTaskFound = tasksPage.isTaskPresentInResults("task");

        Assert.assertTrue(isTaskFound, "Relevant task not found in search results!");
    }
}