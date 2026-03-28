package com.freecrm.automation.pageObjects.deals;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DealsCreatePage {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='title']")
    WebElement txt_title;

    @FindBy(xpath = "//input[@name='amount']")
    WebElement txt_amount;

    @FindBy(xpath = "//input[@class='calendarField']")
    WebElement txt_calendar;

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

    @FindBy(xpath="//i[@class='unlock icon']")
    WebElement unlockIcon;

    @FindBy(xpath = "//div[text()='Select users allowed access.']")
    WebElement usersAllowedAccessDropDown;

    @FindBy(xpath = "//i[@class='settings icon']")
    WebElement settingsButton;

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

    public void enter_calendar(String calendar) {
        txt_calendar.sendKeys(calendar, Keys.ENTER);
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
        WebElement selectOption = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        selectOption.click();
    }

    public void enterCredentials(Integer rowNum, String sheetName) throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> dealsInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), sheetName
        );


        int listIndex = rowNum - 2;
        Map<String, String> deal = dealsInfo.get(listIndex);

        enter_title(deal.get("Title") + " " + ConfigFileReader.getInstance().getBrowser());
        enter_amount(deal.get("Amount"));
        select_stage(deal.get("Stage"));
        select_status(deal.get("Status"));
        select_assigned_to(deal.get("AssignedTo"));
        enter_probability(deal.get("Probablity"));
        enter_calendar(deal.get("Date"));
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

    public void clickUnlockIcon() {
        unlockIcon.click();
    }

    public void selectUsersAllowedAccess(String RowNumber) throws Exception {
        String sheetName = "Credentials";

        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> loginInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), sheetName
        );

        int listIndex = Integer.parseInt(RowNumber) - 2;
        Map<String, String> credentials = loginInfo.get(listIndex);

        String user =  credentials.get("userName");
        usersAllowedAccessDropDown.click();
        SelectOption(user);
    }

    public void logout() throws InterruptedException {
        settingsButton.click();
        Thread.sleep(2000);
        SelectOption("Log Out");

    }

}
