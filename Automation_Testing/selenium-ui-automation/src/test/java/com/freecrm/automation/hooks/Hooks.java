package com.freecrm.automation.hooks;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.enums.DriverType;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static final ThreadLocal<WebDriverManager> webDriverManagerThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<PageObjectManager> pageObjectManagerThreadLocal = new ThreadLocal<>();


    @Before
    public void beforeScenario() {
        if (webDriverManagerThreadLocal.get() == null) {
            WebDriverManager manager = new WebDriverManager();
            webDriverManagerThreadLocal.set(manager);
        }

        WebDriver driver = webDriverManagerThreadLocal.get().getDriver();

        if (pageObjectManagerThreadLocal.get() == null) {
            PageObjectManager pom = new PageObjectManager(driver);
            pageObjectManagerThreadLocal.set(pom);
        }

        DriverType browser = ConfigFileReader.getInstance().getBrowser();
        ExtentCucumberAdapter.getCurrentScenario().assignCategory(String.valueOf(browser));
    }

    @AfterAll
    public static void tearDown() {
        if (webDriverManagerThreadLocal.get() != null) {
            webDriverManagerThreadLocal.get().closeDriver();
            webDriverManagerThreadLocal.remove();
        }
        if (pageObjectManagerThreadLocal.get() != null) {
            pageObjectManagerThreadLocal.remove();
        }
    }

    public static WebDriver getDriver() {
        return webDriverManagerThreadLocal.get().getDriver();
    }

    public static PageObjectManager getPageObjectManager() {
        return pageObjectManagerThreadLocal.get();
    }
}