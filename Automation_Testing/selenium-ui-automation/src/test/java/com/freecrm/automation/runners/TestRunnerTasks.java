package com.freecrm.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/featureFiles/tasks"},
        glue = {"com.freecrm.automation.stepDefinitions.tasks","com.freecrm.automation.hooks"},
        tags="@TC_Task",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false)
public class TestRunnerTasks extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}