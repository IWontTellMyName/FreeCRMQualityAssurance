package com.freecrm.automation.stepDefinitions.contacts;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Steps {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    WebDriver driver;
    WebDriverManager webDriverManager;
    ConfigFileReader configFileReader;
    PageObjectManager pageObjectManager;

//    @Given("User should be logged in")
//    public void user_should_be_logged_in() {
//        configFileReader = ConfigFileReader.getInstance();
//        webDriverManager = new WebDriverManager();
//        driver = webDriverManager.getDriver();
//        driver.get(configFileReader.getApplicationUrl());
//        pageObjectManager = new PageObjectManager(driver);
//        homePage = pageObjectManager.getHomePage();
//        loginPage = pageObjectManager.getLoginPage();
//
//        homePage.clickLogInButton();
//        loginPage.login(configFileReader.getEmail(), configFileReader.getPassword());
//    }
@Given("User should be logged in with {string}")
public void user_should_be_logged_in(String RowNumber) throws IOException {
    configFileReader = ConfigFileReader.getInstance();
    webDriverManager = new WebDriverManager();
    driver = webDriverManager.getDriver();
    driver.get(configFileReader.getApplicationUrl());

    pageObjectManager = new PageObjectManager(driver);
    homePage = pageObjectManager.getHomePage();
    loginPage = pageObjectManager.getLoginPage();

    String sheetName = "Credentials";

    ExcelReader excelReader = new ExcelReader();
    List<Map<String, String>> loginInfo = excelReader.getData(
            ConfigFileReader.getInstance().getExcelFilePath(), sheetName
    );

    int listIndex = Integer.parseInt(RowNumber) - 2;
    Map<String, String> credentials = loginInfo.get(listIndex);

    String email = credentials.get("email");
    String password = credentials.get("password");

    homePage.clickLogInButton();
    loginPage.login(email, password);
//        loginPage.login(configFileReader.getEmail(), configFileReader.getPassword());
}
}


