package com.freecrm.automation.managers;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            driver = createDriver();
            DRIVER.set(driver);
        }
        return driver;
    }


    private WebDriver createDriver() {
        DriverType driverType = ConfigFileReader.getInstance().getBrowser();
        WebDriver driver;
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
            default:
                throw new RuntimeException("Unsupported driver type: " + driverType);
        }

        if (ConfigFileReader.getInstance().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigFileReader.getInstance().getImplicitlyWait()));
        return driver;
    }

    public void closeDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
