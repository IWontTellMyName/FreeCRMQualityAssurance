package com.freecrm.automation.stepDefinitions;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.managers.PageObjectManager;
import com.freecrm.automation.managers.WebDriverManager;
import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Steps {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    WebDriver driver;
    WebDriverManager webDriverManager;
    ConfigFileReader configFileReader;
    PageObjectManager pageObjectManager;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        configFileReader = ConfigFileReader.getInstance();
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        driver.get(configFileReader.getApplicationUrl());
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        loginPage = pageObjectManager.getLoginPage();
        dashboardPage = pageObjectManager.getDashboardPage();
    }

    @When("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        homePage.clickLogInButton();
    }

    @When("enters valid email and password and clicks on login button")
    public void enters_valid_and_and_clicks_on_login_button() {
        loginPage.login(configFileReader.getEmail(), configFileReader.getPassword());
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(dashboardPage.validateDashboardPage());
    }
}
