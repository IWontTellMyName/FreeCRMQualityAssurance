package com.freecrm.automation.hooks;

import com.freecrm.automation.managers.WebDriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Hooks {
    static WebDriverManager webDriverManager;

    @BeforeAll
    public static void setUp() {
        webDriverManager = new WebDriverManager();
    }

    @AfterAll
    public static void tearDown() {
        webDriverManager.closeDriver();
    }

}
