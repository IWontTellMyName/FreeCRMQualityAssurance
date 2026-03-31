package com.freecrm.automation.runners;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(features = {"src/test/resources/featureFiles/tasks"},
        glue = {"com.freecrm.automation.stepDefinitions.tasks","com.freecrm.automation.hooks"},
        tags="@TC_Task",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false)
public class TestRunnerTasks extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)

    @Parameters("browser")
    public void setUpBrowser(@Optional("chrome") String browser) {
        ConfigFileReader.setBrowserForCurrentThread(browser);
    }

    @AfterClass(alwaysRun = true)
    public void clearBrowserOverride() {
        ConfigFileReader.clearBrowserForCurrentThread();
    }
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}