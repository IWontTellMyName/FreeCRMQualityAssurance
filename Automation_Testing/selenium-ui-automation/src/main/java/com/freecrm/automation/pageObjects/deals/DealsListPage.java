package com.freecrm.automation.pageObjects.deals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DealsListPage {

    WebDriver driver;

    @FindBy(xpath = "//span[@class='selectable ' and text()='Deals']")
    WebElement dealsList;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchBox;

    @FindBy(xpath = "//div[@class='content' and text() = 'Deal']/preceding-sibling::i")
    WebElement dealsRecord;

    @FindBy(xpath = "//td/a")
    List<WebElement> dealsRecordsList;

    @FindBy(xpath = "//button[text()='Show Filters']")
    WebElement showFiltersButton;

    @FindBy(xpath = "//div[text()='Search']")
    WebElement filterDropdown;

    @FindBy(xpath = "//span[text()='Stage']")
    WebElement selectStageFilter;

    @FindBy(xpath = "//div[text()='Value']")
    WebElement StageFilterValueDropdown;

    @FindBy(xpath = "//span[text()='Qualify']")
    WebElement selectQualifyStage;

    @FindBy(xpath = "//i[@class='search small icon']")
    WebElement  applyFilterButton;

    @FindBy(xpath = "//td[text()='Qualify']")
    List<WebElement> qualifyStageRecordsList;

    @FindBy(xpath = "(//a[@class='item active'])[2]")
    WebElement noOfRecords;

    @FindBy(xpath = "//i[@class='ban small icon']")
    WebElement clearFilterButton;

    @FindBy(xpath = "//span[text()='Title']")
    WebElement selectTitleFilter;

    @FindBy(xpath = "//input[@placeholder='Value']")
    WebElement valuePlaceholder;


    public DealsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDealsListDisplayed() {
        return dealsList.isDisplayed();
    }

    public void performGlobalSearch(String searchTerm) {
        searchBox.sendKeys(searchTerm, Keys.ENTER);
        System.out.println("Search term entered: " + searchTerm);

    }

    public boolean validateDealsRecord() {
        return dealsRecord.isDisplayed();
    }

    public void clickDealsRecord(){
        driver.navigate().refresh();
        dealsRecord.click();
    }

    public boolean validateGlobalSearch(String searchTerm)
    {
       return dealsRecordsList.stream()
               .map(WebElement::getText)
               .allMatch(record -> record.toLowerCase().contains(searchTerm.toLowerCase()));


    }


    public void clickShowFiltersButton() {
        showFiltersButton.click();
    }

    public void displayFilterDropdown() {
        filterDropdown.click();
    }

    public void selectStageFilter() {
        selectStageFilter.click();
    }

    public void selectQualifyStage(String stage) {
        StageFilterValueDropdown.click();
        selectQualifyStage.click();
    }

    public void clickApplyFilterButton() {
        applyFilterButton.click();
    }

    public int noOfRecords() {
        String recordsText =noOfRecords.getText();
        return Integer.parseInt(recordsText);
    }

    public boolean validateFilterResults(){
        return qualifyStageRecordsList.size() == noOfRecords();
    }

    public void clickClearFilterButton() {
        clearFilterButton.click();
    }

    public void selectTitleFilter() {
        selectTitleFilter.click();
    }

    public void enterTitle(String title) {
        valuePlaceholder.sendKeys(title);
    }

    public boolean validateNoRecords()
    {
        return driver.findElement(By.xpath("//p[text()='No records found']")).isDisplayed();
    }


}
