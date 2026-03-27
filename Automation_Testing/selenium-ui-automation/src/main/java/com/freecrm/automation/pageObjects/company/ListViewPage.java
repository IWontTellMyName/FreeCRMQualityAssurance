package com.freecrm.automation.pageObjects.company;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ListViewPage {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Create']")
    public WebElement createButton;

    @FindBy(xpath = "//button[contains(text(),'Filters')]")
    public WebElement filterToggle;

    @FindBy(xpath = "//tbody//input[@type='checkbox']")
    public List<WebElement> listItemCheckboxes;

    public ListViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validateListViewPage() {
        return createButton.isDisplayed() && filterToggle.isDisplayed();
    }

}