package com.freecrm.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/featureFiles/contacts"},
        glue = {"com.freecrm.automation.stepDefinitions.contacts"},
        dryRun = false)
public class TestRunnerContacts extends AbstractTestNGCucumberTests {
}
