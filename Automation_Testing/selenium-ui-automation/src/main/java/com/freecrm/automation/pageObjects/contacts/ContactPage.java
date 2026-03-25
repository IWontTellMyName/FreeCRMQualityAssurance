package com.freecrm.automation.pageObjects.contacts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ContactPage {
    WebDriver driver;

    // Locator for Contacts header
    @FindBy(xpath = "//span[@class='selectable ']")
    WebElement contactsHeader;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to verify Contacts page
    public boolean validateContactsPage() {
        return contactsHeader.isDisplayed() &&
                driver.getCurrentUrl().contains("contacts");
    }
}

