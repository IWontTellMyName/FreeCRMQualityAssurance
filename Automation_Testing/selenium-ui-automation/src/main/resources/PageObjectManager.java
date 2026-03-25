package com.freecrm.automation.managers;

import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

@Getter
public class PageObjectManager {

    private WebDriver driver;

    private HomePage homePage;

    private LoginPage loginPage;
}
