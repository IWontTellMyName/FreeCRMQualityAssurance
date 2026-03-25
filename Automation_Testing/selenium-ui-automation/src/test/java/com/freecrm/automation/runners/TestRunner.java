package com.freecrm.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/featureFiles"},
        glue = {"com.freecrm.automation.stepDefinitions", "com.freecrm.automation.hooks"},
        dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {
}
