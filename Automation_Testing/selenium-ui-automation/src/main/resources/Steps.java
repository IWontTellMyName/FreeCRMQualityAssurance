package com.freecrm.automation.stepDefinitions;

import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Steps {
    HomePage homePage;
    LoginPage loginPage;
    WebDriver driver;
    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.freecrm.com/");
        homePage = new HomePage(driver);
    }
    @When("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        homePage.clickLogInButton();
        loginPage = new LoginPage(driver);
    }
    @When("enters valid {string} and {string} and clicks on login button")
    public void enters_valid_and_and_clicks_on_login_button(String email, String password) {
        loginPage.login(email, password);
    }
    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
