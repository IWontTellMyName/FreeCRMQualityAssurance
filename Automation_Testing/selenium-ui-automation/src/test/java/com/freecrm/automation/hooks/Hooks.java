package com.freecrm.automation.hooks;

import com.aventstack.extentreports.service.ExtentService;
import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.managers.WebDriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.testng.annotations.*;

public class Hooks {
    static WebDriverManager webDriverManager;
    @BeforeAll
    public static void setUp() {
        webDriverManager = new WebDriverManager();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpBrowser(@Optional("chrome") String browser) {
        ConfigFileReader.setBrowserForCurrentThread(browser);
    }

    @AfterClass(alwaysRun = true)
    public void clearBrowserOverride() {
        ConfigFileReader.clearBrowserForCurrentThread();
    }


    @AfterAll
    public static void tearDown() {
        webDriverManager.closeDriver();
    }

}
