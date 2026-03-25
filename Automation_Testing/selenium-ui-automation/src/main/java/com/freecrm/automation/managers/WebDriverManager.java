package com.freecrm.automation.managers;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;
    private static DriverType driverType;


    public WebDriverManager() {
        driverType = ConfigFileReader.getInstance().getBrowser();
    }

    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }


    private WebDriver createDriver() {
        switch (driverType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
        }

        if (ConfigFileReader.getInstance().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigFileReader.getInstance().getImplicitlyWait()));
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


