package com.freecrm.automation.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(name = "email")
    private WebElement txt_email;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        try {
            txt_email.sendKeys(email, Keys.TAB, password, Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Already logged in");
        }
    }

}
