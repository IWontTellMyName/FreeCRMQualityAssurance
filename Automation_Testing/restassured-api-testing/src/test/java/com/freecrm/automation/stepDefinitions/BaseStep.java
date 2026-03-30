package com.freecrm.automation.stepDefinitions;

import com.freecrm.automation.apiEngine.ApiService;
import com.freecrm.automation.cucumber.ScenarioContext;
import com.freecrm.automation.cucumber.TestContext;
import com.freecrm.automation.utils.ConfigReader;
import lombok.Getter;

@Getter
public class BaseStep {

    private final ScenarioContext scenarioContext;
    private final ApiService apiService;
    private final ConfigReader configReader;

    public BaseStep(TestContext testContext) {
        this.scenarioContext = testContext.getScenarioContext();
        this.apiService = testContext.getApiService();
        this.configReader = testContext.getConfigReader();
    }

}
