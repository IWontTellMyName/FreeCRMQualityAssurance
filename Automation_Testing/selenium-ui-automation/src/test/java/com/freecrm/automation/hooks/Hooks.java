package com.freecrm.automation.hooks;

import com.aventstack.extentreports.service.ExtentService;
import com.freecrm.automation.managers.WebDriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Hooks {
    static WebDriverManager webDriverManager;
    @BeforeAll
    public static void setUp() {
        webDriverManager = new WebDriverManager();
        ExtentService.getInstance().setSystemInfo("Environment", "QA");
        ExtentService.getInstance().setSystemInfo("OS", "Windows 11");
        ExtentService.getInstance().setSystemInfo("Java Version", "21");
    }

    @AfterAll
    public static void tearDown() {
        webDriverManager.closeDriver();
    }

}
