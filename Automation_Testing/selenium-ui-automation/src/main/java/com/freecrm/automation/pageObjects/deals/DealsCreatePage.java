package com.freecrm.automation.pageObjects.deals;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DealsCreatePage {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='title']")
    WebElement txt_title;

    @FindBy(xpath = "//input[@name='amount']")
    WebElement txt_amount;

    @FindBy(xpath = "//span[@class='react-datepicker__navigation-icon react-datepicker__navigation-icon--next']")
    WebElement btn_next;

    @FindBy(xpath = "//div[@aria-label='Choose Friday, 17 April 2026']")
    WebElement btn_chooseFriday;

    @FindBy(xpath = "//label[text()='Stage']/..//div[text()='Select']")
    WebElement stageDropDown;

    @FindBy(xpath = "//label[text()='Status']/..//div[text()='Select']")
    WebElement statusDropDown;

    @FindBy(xpath = "//label[text()='Assigned To']/..//i")
    WebElement assignedToDropDown;

    private static final String SELECT_OPTION = "//span[text()='%s']";

    @FindBy(xpath = "//input[@name='probability']")
    WebElement txt_probability;

    @FindBy(xpath = "//i[@class='save icon']")
    WebElement btn_save;

    @FindBy(xpath = "//i[@class='large money red icon']")
    WebElement largeMoneyicon;

    @FindBy(xpath = "//i[@class='money icon']")
    WebElement moneyIcon;

    @FindBy(xpath = "//span[@class='inline-error-msg' and text()='The field Title is required.']")
    WebElement titleValidation;

//    @FindBy(xpath = "//p[text()='Probability must be between 0 and 100']")
//    WebElement probabilityValidation;

    public DealsCreatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enter_title(String title) {
        txt_title.sendKeys(title);
    }

    public void enter_amount(String amount) {
        txt_amount.sendKeys(amount);
    }

    public void enter_probability(String probability) {
        txt_probability.sendKeys(probability);
    }

    public void click_next() {
        btn_next.click();
    }

    public void click_chooseFriday() {
        btn_chooseFriday.click();
    }

    public void click_save() {
        btn_save.click();
    }

    public void select_stage(String stage) {
        stageDropDown.click();
        SelectOption(stage);

    }

    public void select_status(String status) {
        statusDropDown.click();
        SelectOption(status);
    }

    public void select_assigned_to(String assigned_to) {
        assignedToDropDown.click();
        SelectOption(assigned_to);
    }

    private void SelectOption(String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By locator = By.xpath(String.format(SELECT_OPTION, option));
        WebElement statusOption = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(statusOption));
        statusOption.click();
    }

    public void enterCredentials() throws InterruptedException {
        enter_title("Test Deal");
        enter_amount("1000");
//        click_next();
//        click_chooseFriday();
        select_stage("Qualify");
        select_status("Active");
        select_assigned_to("Sudeshna Pathak");
        enter_probability("50");
    }

    public boolean validate_deal_creation() throws InterruptedException {
        Thread.sleep(2000);
        return largeMoneyicon.isDisplayed();
    }

    public void clickMoneyIcon() {
        moneyIcon.click();
    }

    public boolean validateTitleRequired() {
        return titleValidation.isDisplayed();
    }

    public boolean validateProbabilityRequired() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//p[contains(text(), 'Probability')]")
                )
        );
        return errorMsg.isDisplayed();
    }

}
