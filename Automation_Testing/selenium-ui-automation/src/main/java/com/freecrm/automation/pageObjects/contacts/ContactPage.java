package com.freecrm.automation.pageObjects.contacts;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    //create contact
    @FindBy(xpath = " (//*[@class='edit icon'])[1]")
    WebElement createButton;

    @FindBy(xpath = "//input[@name='first_name']")
    WebElement firstName;

    @FindBy(xpath = "//label[text()='Last Name']/following::input[1]")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Email address']")
    WebElement email;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveButton;

    public void clickCreateButton() {
        createButton.click();
    }

    public void enterFirstName(String fname) {
        firstName.sendKeys(fname);
    }

    public void enterLastName(String lname) {
        lastName.sendKeys(lname);
    }

    public void enterEmail(String mail) {
        email.sendKeys(mail);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean isContactCreated() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until URL changes from /contacts/new
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/contacts/new")
        ));

        String url = driver.getCurrentUrl();
        System.out.println("Final URL: " + url);

        return url.contains("/contacts/");
    }


    //search contact
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchBox;

    public void searchContact(String name) {
        searchBox.sendKeys(name + Keys.ENTER);
    }
    public boolean isSearchResultDisplayed(String name) {
        String xpath = "//a[contains(text(),'" + name + "')]";
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }


    // DELETE CONTACT

    public void selectContact(String name) {
        String xpath = "//a[text()='" + name + "']/ancestor::tr//div[@class='ui fitted checkbox']/label";

        WebElement checkbox = driver.findElement(By.xpath(xpath));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", checkbox);

        // Hover + click
        Actions actions = new Actions(driver);
        actions.moveToElement(checkbox).click().perform();
    }


    public void clickDeleteContact(String name) {
        String xpath = "//a[text()='" + name + "']/ancestor::tr//button[i[@class='trash icon']]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );

        deleteBtn.click();
    }


    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement confirmBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'modal')]//button[contains(@class,'red')]")
                )
        );

        confirmBtn.click();
    }


    public boolean isContactDeleted(String name) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(driver ->
                !driver.getPageSource().contains(name)
        );
    }

    //validate mandatory fields
    @FindBy(xpath = "//span[contains(@class,'inline-error-msg') and contains(text(),'First Name')]")
    WebElement firstNameError;

    @FindBy(xpath = "//span[contains(@class,'inline-error-msg') and contains(text(),'Last Name')]")
    WebElement lastNameError;

    public boolean isFirstNameErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(firstNameError)).isDisplayed();
    }

    public boolean isLastNameErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(lastNameError)).isDisplayed();
    }


    //filter
    @FindBy(xpath = "//button[contains(text(),'Show Filters')]")
    WebElement showFiltersBtn;

    public void clickShowFilters() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(showFiltersBtn)).click();
    }

    @FindBy(xpath = "//div[@name='name' and contains(@class,'dropdown')]")
    WebElement searchDropdown;

    public void clickSearchDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchDropdown)).click();
    }

    public void selectFilterField(String field) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String optionXpath = "//span[text()='" + field + "']";

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(optionXpath))
        );

        option.click();
    }

    @FindBy(xpath = "//input[@name='value']")
    WebElement filterValue;

    public void enterFilterValue(String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement input = wait.until(
                ExpectedConditions.visibilityOf(filterValue)
        );

        input.clear();
        input.sendKeys(value);
    }

    @FindBy(xpath = "//button[contains(.,'Filter')]")
    WebElement filterButton;

    public void clickFilterButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }

    public boolean isFilteredResultDisplayed(String name) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String xpath = "//table//tbody//a[contains(text(),'" + name + "')]";

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );

        return result.isDisplayed();
    }
}

