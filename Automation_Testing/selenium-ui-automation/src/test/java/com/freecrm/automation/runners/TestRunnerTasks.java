package com.freecrm.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/featureFiles/tasks"},
        glue = "com.freecrm.automation.stepDefinitions.tasks",
        tags="@TC_Task_001",
        dryRun = false)
public class TestRunnerTasks extends AbstractTestNGCucumberTests {
}