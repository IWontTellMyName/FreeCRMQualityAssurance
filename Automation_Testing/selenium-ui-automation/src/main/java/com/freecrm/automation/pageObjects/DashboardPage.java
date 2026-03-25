package com.freecrm.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;

    @FindBy(xpath="//div[text()='Deals Summary']")
    WebElement dealsSummary;

    @FindBy(xpath = "//i[@class='users icon']")
    WebElement contacts;

    @FindBy(xpath = "//i[@class='building icon']")
    WebElement company;

    @FindBy(xpath = "//i[@class='money icon']")
    WebElement deals;

    @FindBy(xpath = "//i[@class='tasks icon']")
    WebElement tasks;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validateDashboardPage() {
        return dealsSummary.isDisplayed();
    }

    public void clickContactsIcon() {
        contacts.click();
    }

    public void clickCompanyIcon() {
        company.click();
    }

    public void clickDealsIcon() {
        deals.click();
    }

    public void clickTasksIcon() {
        tasks.click();
    }

}
