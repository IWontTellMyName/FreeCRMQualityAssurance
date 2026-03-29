package com.freecrm.automation.runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.*;

import com.freecrm.automation.dataProviders.ConfigFileReader;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/featureFiles/company"},
        glue = {"com.freecrm.automation.stepDefinitions.company", "com.freecrm.automation.hooks"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false)
public class TestRunnerCompany extends AbstractTestNGCucumberTests {

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

