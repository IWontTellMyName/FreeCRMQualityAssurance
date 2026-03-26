package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;

import java.time.Duration;

public class CreateTask {

    DashboardPage dashboardPage;
    WebDriverManager webDriverManager;
    TasksPage tasksPage;

    @Given("the user clicks on the Tasks tab in the main navigation menu")
    public void the_user_clicks_on_the_tasks_tab_in_the_main_navigation_menu() {
        webDriverManager = new WebDriverManager();
        dashboardPage = new DashboardPage(webDriverManager.getDriver());
        dashboardPage.clickTasksIcon();
    }
    @Given("the user clicks the create button to open the task creation form")
    public void the_user_clicks_the_create_button_to_open_the_task_creation_form() {

        tasksPage = new TasksPage(webDriverManager.getDriver());

        tasksPage.clickCreate();   // ✅ clean, reusable, stable
    }
    @Then("the task creation form should be displayed")
    public void the_task_creation_form_should_be_displayed() {
        Assert.assertTrue(tasksPage.isTaskPageLoaded());
    }

    @When("the user enters task title {string}")
    public void the_user_enters_task_title(String title) {
        tasksPage.enterTitle(title);
    }

//    @When("selects an assigned user")
//    public void selects_an_assigned_user() throws InterruptedException {
//        tasksPage.selectAssignedUser();
//    }

    @When("selects a valid due date")
    public void selects_a_valid_due_date() {
        tasksPage.enterDueDate("25/04/2026"); // static for now
    }

    @When("clicks on Save")
    public void clicks_on_save() {
        tasksPage.clickSave();
    }

    @Then("the task should be created successfully")
    public void the_task_should_be_created_successfully() throws InterruptedException{
        // Validate redirect to task details page
        String currentUrl = webDriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/tasks/"));
        Thread.sleep(3000);
    }

    @When("the user navigates to the task list")
    public void the_user_navigates_to_the_task_list() {
        dashboardPage.clickTasksIcon();
    }

    @Then("the created task {string} should be visible in the task list")
    public void the_created_task_should_be_visible_in_the_task_list(String taskName) {
        WebDriverWait wait = new WebDriverWait(webDriverManager.getDriver(), Duration.ofSeconds(15));

// Step 1: Wait for table
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

// Step 2: Wait for rows
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr")));

// Step 3: Find task
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody//a[normalize-space()='" + taskName + "']")
        ));

        Assert.assertTrue(task.isDisplayed());

    }
}