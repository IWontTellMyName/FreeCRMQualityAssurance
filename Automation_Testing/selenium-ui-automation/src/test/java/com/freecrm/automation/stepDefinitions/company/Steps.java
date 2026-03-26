package com.freecrm.automation.stepDefinitions.company;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class Steps {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    WebDriver driver;
    WebDriverManager webDriverManager;
    ConfigFileReader configFileReader;
    PageObjectManager pageObjectManager;

    @Given("User should be logged in")
    public void user_should_be_logged_in() {
        configFileReader = ConfigFileReader.getInstance();
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        driver.get(configFileReader.getApplicationUrl());
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        loginPage = pageObjectManager.getLoginPage();
        homePage.clickLogInButton();
        loginPage.login(configFileReader.getEmail(), configFileReader.getPassword());
    }
    
}


