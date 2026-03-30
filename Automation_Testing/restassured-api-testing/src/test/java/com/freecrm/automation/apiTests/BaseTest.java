package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.ApiService;
import com.freecrm.automation.context.ScenarioContext;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.utils.ConfigReader;
import io.qameta.allure.testng.AllureTestNg;
import lombok.Getter;
import org.testng.annotations.Listeners;

@Getter
@Listeners(AllureTestNg.class)
public class BaseTest {

    private final ScenarioContext scenarioContext;
    private final ApiService apiService;
    private final ConfigReader configReader;

    public BaseTest(TestContext testContext) {
        this.scenarioContext = testContext.getScenarioContext();
        this.apiService = testContext.getApiService();
        this.configReader = testContext.getConfigReader();
    }

}
