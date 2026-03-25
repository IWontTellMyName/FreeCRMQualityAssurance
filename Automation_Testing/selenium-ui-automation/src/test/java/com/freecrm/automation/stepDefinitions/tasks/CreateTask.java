package com.freecrm.automation.stepDefinitions.tasks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateTask {
    @When("the user enters task title {string}")
    public void the_user_enters_task_title(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("selects an assigned user")
    public void selects_an_assigned_user() {
        // Write code here that turns the phrase above
    }
    @When("selects a valid due date")
    public void selects_a_valid_due_date() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("clicks on Save")
    public void clicks_on_save() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the task should be created successfully")
    public void the_task_should_be_created_successfully() {

    }
    @When("the user navigates to the task list")
    public void the_user_navigates_to_the_task_list() {

    }
    @Then("the created task {string} should be visible in the task list")
    public void the_created_task_should_be_visible_in_the_task_list(String string) {
    }
}
