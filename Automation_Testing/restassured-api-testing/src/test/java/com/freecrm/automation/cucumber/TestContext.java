package com.freecrm.automation.cucumber;

import com.freecrm.automation.apiEngine.ApiService;
import com.freecrm.automation.utils.ConfigReader;
import lombok.Getter;

@Getter
public class TestContext {
    private final ScenarioContext scenarioContext;
    private final ConfigReader configReader = ConfigReader.getInstance();
    private final ApiService apiService;


    public TestContext() {
        this.scenarioContext = new ScenarioContext();
        this.apiService = new ApiService(configReader.getBaseUrl());
    }
}
