package com.freecrm.automation.managers;

import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
        private final WebDriver driver;
        private HomePage homePage;
        private LoginPage loginPage;
        private DashboardPage dashboardPage;

        public PageObjectManager(WebDriver driver) {
            this.driver = driver;
        }

        public HomePage getHomePage() {
            return (homePage == null) ? homePage = new HomePage(driver) : homePage;
        }

        public LoginPage getLoginPage() {
            return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
        }

        public DashboardPage getDashboardPage() {
            return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
        }
}
