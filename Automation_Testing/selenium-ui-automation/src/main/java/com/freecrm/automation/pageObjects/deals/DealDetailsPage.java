package com.freecrm.automation.pageObjects.deals;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DealDetailsPage {

    WebDriver driver;
    private static Map<String, String> dealsInfo;

    @FindBy(xpath = "//i[@class='edit icon']")
    WebElement editIcon;

    @FindBy(xpath = "//label[text()='Company']/..//input")
    WebElement companyName;

    @FindBy(xpath = "//div[@name='contacts']/..//input")
    WebElement contactName;

    @FindBy(xpath = "//div[@name='products']/..//input")
    WebElement productName;

    @FindBy(xpath = "//h4[text()='Tasks']/..//i[@class='linkify icon']")
    WebElement linkTask;

    @FindBy(xpath = "//h4[text()='Tasks']/following-sibling::div//input")
    WebElement taskName;

    @FindBy(xpath = "//i[@class='check icon']")
    WebElement checkIn;

    @FindBy(xpath = "//div[text()='Events']/..//button")
    WebElement addEvent;

    @FindBy(xpath = "//input[@name='title']")
    WebElement txt_EventTitle;

    @FindBy(xpath = "//i[@class='calendar icon']")
    WebElement calendar;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement save;

    private static final String VERIFY_FEILDS = "//div[contains(text(),'%s')]";
    private static final String VERIFY_EVENT = "//span[contains(text(),'%s')]";
    private static final String VERIFY_TASK = "//a[text()='%s']";


    public DealDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String DEAL_TITLE_XPATH = "//td[2]/a[text()='%s']";

    public void openDealDetailsPage(String dealTitle) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By locator = By.xpath(String.format(DEAL_TITLE_XPATH, dealTitle + " " + ConfigFileReader.getInstance().getBrowser()));
        WebElement deal = wait.until(ExpectedConditions.elementToBeClickable(locator));
        deal.click();
    }

    public void clickEditIcon() {
        editIcon.click();
    }

    public void retriveDealFromExcel(String rowNum, String sheetName) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> deals = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), sheetName
        );

        int listIndex = Integer.parseInt(rowNum) - 2;
        dealsInfo = deals.get(listIndex);
    }

    public void editDeal(String rowNum, String sheetName) throws InterruptedException, IOException {
        retriveDealFromExcel(rowNum, sheetName);
        Thread.sleep(2000);
        companyName.sendKeys(dealsInfo.get("Company"));
        Thread.sleep(2000);
        companyName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(2000);
        contactName.sendKeys(dealsInfo.get("Contact"));
        Thread.sleep(2000);
        contactName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(2000);
        productName.sendKeys(dealsInfo.get("Product"));
        Thread.sleep(2000);
        productName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void linkTask() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", linkTask);

        wait.until(ExpectedConditions.elementToBeClickable(linkTask)).click();

        String TaskName = dealsInfo.get("Task");

        wait.until(ExpectedConditions.visibilityOf(taskName)).sendKeys(TaskName);
        Thread.sleep(2000);
        taskName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(checkIn)).click();
    }

    public void addEvent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addEvent);

        wait.until(ExpectedConditions.elementToBeClickable(addEvent)).click();

        String EventTitle = dealsInfo.get("Event");
        wait.until(ExpectedConditions.visibilityOf(txt_EventTitle))
                .sendKeys(EventTitle, Keys.ENTER);
    }

    public boolean verifyDealEdit() throws InterruptedException {
        Thread.sleep(2000);
        By companyLocator =
                By.xpath(String.format(VERIFY_FEILDS, dealsInfo.get("Company")));
        By contactLocator =
                By.xpath(String.format(VERIFY_FEILDS, dealsInfo.get("Contact")));
        By productLocator =
                By.xpath(String.format(VERIFY_FEILDS, dealsInfo.get("Product")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement company = wait.until(ExpectedConditions.visibilityOfElementLocated(companyLocator));
        WebElement contact = wait.until(ExpectedConditions.visibilityOfElementLocated(contactLocator));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addEvent);
        Thread.sleep(2000);
        By eventLocator =
                By.xpath(String.format(VERIFY_EVENT, dealsInfo.get("Event")));

        WebElement event = wait.until(ExpectedConditions.visibilityOfElementLocated(eventLocator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", linkTask);
        Thread.sleep(2000);
        By taskLocator = By.xpath(String.format(VERIFY_TASK, dealsInfo.get("Task")));
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(taskLocator));

        return company.isDisplayed() && contact.isDisplayed() && product.isDisplayed() && event.isDisplayed() && task.isDisplayed();

    }

    public boolean openCalender() throws InterruptedException {
        calendar.click();
        Thread.sleep(2000);
        By eventLocator =
                By.xpath(String.format(VERIFY_EVENT, dealsInfo.get("Event")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement event = wait.until(ExpectedConditions.visibilityOfElementLocated(eventLocator));
        return event.isDisplayed();
    }

    public void clickSave() throws InterruptedException {
        Thread.sleep(2000);
        save.click();
        Thread.sleep(2000);
    }

}
