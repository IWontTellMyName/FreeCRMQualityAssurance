package com.freecrm.automation.pageObjects.deals;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DealsPipelinePage {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Pipeline']")
    WebElement pipelineButton;

    private static final String SELECT_DROPDOWN = "(//label[text()='Select fields']/..//i[@class='dropdown icon'])[%d]";

    private static final String SELECT_OPTION =
            "(//div[@role='option']//span[normalize-space()='%s'])[%d]";

    @FindBy(xpath = "//button[text()='+']")
    WebElement addButton;

    @FindBy(xpath = "//input[@name='pipeline_name']")
    WebElement pipelineName;

    @FindBy(xpath = "//button[@class='ui primary button' and text()='Create']")
    WebElement createButton;

    @FindBy(xpath = "//a[@data-tab='amount']")
    WebElement amountTab;

    @FindBy(xpath = "//a[@data-tab='values__stage']")
    WebElement stageTab;

    @FindBy(xpath = "//a[@data-tab='values__status']")
    WebElement statusTab;

    private static final String VERIFY_GRAPH_TITLE = "//*[name()='tspan' and normalize-space()='%s']";

    private static final String VERIFY_TABLE_HEADER = "//th[text()='%s']";

    @FindBy(xpath = "//span[text()='Deal Pipeline']")
    WebElement dealPipeline;


    public DealsPipelinePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickPipelineButton() {
        pipelineButton.click();
    }

    public void selectFieldDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String[] fields = {"Stage", "Amount", "Status"};
        int cnt = 1;
        for (String field : fields) {

            By locator = By.xpath(String.format(SELECT_DROPDOWN, cnt));
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));
            dropdown.click();

            locator = By.xpath(String.format(SELECT_OPTION, field, cnt));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(locator));
            option.click();

            wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

            cnt++;
        }
    }

    public void EnterPipelineName(String name) {
        pipelineName.sendKeys(name + ConfigFileReader.getInstance().getBrowser());
    }

    public void clickCreateButton() {
        createButton.click();
    }


    public boolean verifyPipelineCreation() throws InterruptedException {
        Thread.sleep(2000);
        return dealPipeline.isDisplayed();
    }

    public boolean verifyGraphTitleAndTableHeader(String category) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By locator = By.xpath(String.format(VERIFY_GRAPH_TITLE, category));
        WebElement graphHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        locator = By.xpath(String.format(VERIFY_TABLE_HEADER, category));
        WebElement tableTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        return graphHeader.isDisplayed() && tableTitle.isDisplayed();
    }

    public boolean verifyPipelineFilteringAndGrouping() throws InterruptedException {
        boolean res = true;
        amountTab.click();
        Thread.sleep(2000);
        res &= verifyGraphTitleAndTableHeader("Amount");

        stageTab.click();
        Thread.sleep(2000);
        res &= verifyGraphTitleAndTableHeader("Stage");

        statusTab.click();
        Thread.sleep(2000);
        res &= verifyGraphTitleAndTableHeader("Status");

        return res;

    }

}
