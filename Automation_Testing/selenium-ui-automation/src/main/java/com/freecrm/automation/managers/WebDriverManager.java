package com.freecrm.automation.managers;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--headless");  // ✅ important
                driver = new FirefoxDriver(ffOptions);
                break;

            case CHROME:
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless=new");  // ✅ critical for Jenkins
                chOptions.addArguments("--no-sandbox");
                chOptions.addArguments("--disable-dev-shm-usage");
                chOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new"); // ✅ important
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Unsupported driver type: " + driverType);
        }

        if (ConfigFileReader.getInstance().getBrowserWindowSize()) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigFileReader.getInstance().getImplicitlyWait())
        );

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
