package com.freecrm.automation.hooks;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.enums.DriverType;
import com.freecrm.automation.managers.WebDriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
    static WebDriverManager webDriverManager;

    @BeforeAll
    public static void setUp() {
        webDriverManager = new WebDriverManager();
    }

    @Before
    public void beforeScenario() {

        DriverType browser = ConfigFileReader.getInstance().getBrowser();

        ExtentCucumberAdapter.getCurrentScenario()
                .assignCategory(String.valueOf(browser));
    }

    @AfterAll
    public static void tearDown() {
        webDriverManager.closeDriver();
    }

}
