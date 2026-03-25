package com.freecrm.automation.pageObjects.deals;

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
    WebElement SelectStageFilter;

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

    public DealsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDealsListDisplayed() {
        return dealsList.isDisplayed();
    }

    public void performGlobalSearch(String searchTerm) {
        searchBox.sendKeys(searchTerm, Keys.ENTER);
    }

    public void validateDealsRecord() {
        dealsRecord.isDisplayed();
    }

    public void clickDealsRecord(){
        driver.navigate().refresh();
        dealsRecord.click();
    }

    public boolean validateGlobalSearch(String searchTerm)
    {
        driver.navigate().back();
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
        SelectStageFilter.click();
    }

    public void selectQualifyStage() {
        selectQualifyStage.click();
    }

    public void clickApplyFilterButton() {
        applyFilterButton.click();
    }

    public boolean validateFilterResults(){
        return qualifyStageRecordsList.size() == Integer.parseInt(noOfRecords.getText());
    }

}
