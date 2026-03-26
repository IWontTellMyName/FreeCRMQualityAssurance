package com.freecrm.automation.stepDefinitions.tasks;

import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TitleLengthValidation {

    WebDriverManager webDriverManager = new WebDriverManager();
    TasksPage tasksPage = new TasksPage(webDriverManager.getDriver());

    @When("the user enters a task title of length 260")
    public void the_user_enters_a_task_title_exceeding_250_characters() throws InterruptedException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 260; i++) {
            sb.append("a");
        }

        String longTitle = sb.toString();

        tasksPage.enterTitle(longTitle);
    }
    @Then("a length error message {string} should be displayed")
    public void a_length_error_message_should_be_displayed(String expectedMessage) {

        String actualMessage;

        if (expectedMessage.contains("250")) {
            actualMessage = tasksPage.getTitleLengthErrorMessage();
        } else {
            actualMessage = tasksPage.getTitleErrorMessage();
        }

        Assert.assertEquals(actualMessage, expectedMessage,
                "Validation message mismatch!");
    }
}