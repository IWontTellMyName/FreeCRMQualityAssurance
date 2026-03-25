package com.freecrm.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/featureFiles/deals"},
        glue = {"com.freecrm.automation.stepDefinitions.deals", "com.freecrm.automation.hooks"},
        dryRun = false)
public class TestRunnerDeals extends AbstractTestNGCucumberTests {
}

