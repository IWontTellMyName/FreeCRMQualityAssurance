package com.freecrm.automation.pageObjects.tasks;

import com.freecrm.automation.dataProviders.ConfigFileReader;
import com.freecrm.automation.dataProviders.ExcelReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class TasksPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    WebElement createButton;

    @FindBy(name = "title")
    WebElement titleInput;
    //div[@role='listbox' and @aria-multiselectable='false' and @tabindex='0']
    //label[text()='Assigned To']/..//i
    @FindBy(xpath = "//label[text()='Assigned To']/..//i")
    WebElement assignedToDropdown;

    @FindBy(xpath = "//input[contains(@class,'calendarField')]")
    WebElement dueDateInput;

    @FindBy(xpath = "//label[text()='Status']/..//div[text()='Select']")
    WebElement statusDropDown;

    @FindBy(xpath = "//button[contains(@class,'linkedin')]")
    WebElement saveButton;

    @FindBy(xpath = "//button[contains(@class,'button') and contains(text(),'Cancel')]")
    WebElement cancelButton;

    @FindBy(className = "inline-error-msg")
    WebElement titleErrorMessage;

    @FindBy(xpath = "//p[text()='Title is longer than 250 characters']")
    WebElement titleLengthErrorMessage;

    @FindBy(xpath = "//span[text()='Tasks']")
    WebElement tasksHeader;

    @FindBy(xpath = "//table[contains(@class,'custom-grid')]")
    WebElement taskTable;

    @FindBy(xpath = "//p[text()='New_Task']")
    WebElement taskTitleDisplay;

    @FindBy(xpath = "//div[@class='actions']//button[text()='Cancel']")
    WebElement cancelDeleteButton;

    @FindBy(xpath = "//div[@class='actions']//button[contains(@class,'red')]")
    WebElement confirmDeleteButton;

    //search
    @FindBy(xpath="//input[@placeholder='Search']")
    WebElement searchBox;

    @FindBy(xpath="//table//tbody//tr//td//a")
    List<WebElement> taskResults;

    @FindBy(xpath = "//h3[contains(.,'Task')]")
    WebElement taskHeader;

    // Constructor

    public TasksPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions

    //create

    public void enter_title(String title) {
        titleInput.sendKeys(title);
    }
    public void enter_duedate(String due_date ) {
        dueDateInput.sendKeys(due_date);
    }
    public void select_assigned_to(String assigned_to) throws InterruptedException {
        assignedToDropdown.click();
        Thread.sleep(3000);
        SelectOption(assigned_to);
    }

    private static final String SELECT_OPTION = "//span[text()='%s']";

    private void SelectOption(String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By locator = By.xpath(String.format(SELECT_OPTION, option));
        WebElement selectOption = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        selectOption.click();
    }
    public void select_status(String status) {
        statusDropDown.click();
        SelectOption(status);
    }

    public void enterCredentials(Integer rowNum, String sheetName) throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> dealsInfo = excelReader.getData(
                ConfigFileReader.getInstance().getExcelFilePath(), sheetName
        );


        int listIndex = rowNum - 2;
        Map<String, String> task = dealsInfo.get(listIndex);

        enter_title(task.get("Title"));
        enter_duedate(task.get("Due Date"));
        select_assigned_to(task.get("AssignedTo"));
        select_status(task.get("Status"));
    }

    public void clickCreate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(createButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", createButton);
    }
    public void clickTaskByName(String taskName) throws InterruptedException {
        Thread.sleep(3000);
        WebElement req=driver.findElement(By.xpath("//div[@class='header item']"));
        req.click();
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody//a[normalize-space()='" + taskName + "']")
        ));
        wait.until(ExpectedConditions.elementToBeClickable(task)).click();
    }

    public void clickEditButton() {
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@href,'/tasks/edit')]//button)[1]")
        ));
        editBtn.click();
    }
//    public void enterTitle(String title) {
//        titleInput.clear();
//        titleInput.sendKeys(title);
//    }
    public void enterTitle(String title) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(titleInput));
        titleInput.click();
        Thread.sleep(2000);
        titleInput.sendKeys(Keys.CONTROL + "a");
        Thread.sleep(2000);
        titleInput.sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        titleInput.sendKeys(title);
    }

    public void selectAssignedUser() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        assignedToDropdown.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Subhangi Pandey']")).click();

    }
    public void enterDueDate(String date) {
        dueDateInput.clear();
        dueDateInput.sendKeys(date);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public String getTitleErrorMessage() {
        return titleErrorMessage.getText();
    }

    public String getTitleLengthErrorMessage(){
        return titleLengthErrorMessage.getText();
    }

    public void clickDeleteForTask(String taskName) {

        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath(
                        "//tbody//tr[.//a[normalize-space()='" + taskName + "']]//i[contains(@class,'trash')]"
                ))
        ));

        deleteBtn.click();
    }

    public void clickConfirmDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteButton.click();
    }

    public void clickCancelDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelDeleteButton));
        cancelDeleteButton.click();
    }

    // Validation

    public boolean isTaskPageLoaded() {
        return titleInput.isDisplayed();
    }
    //
    public boolean isTasksPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(tasksHeader)).isDisplayed();
    }

    public boolean isTaskTableVisible() {
        return wait.until(ExpectedConditions.visibilityOf(taskTable)).isDisplayed();
    }

    public String getDisplayedTaskTitle() {
        return wait.until(ExpectedConditions.visibilityOf(taskTitleDisplay)).getText();
    }

    public boolean isTaskPresentInList(String taskName) {
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody//a[normalize-space()='" + taskName + "']")
        ));
        return task.isDisplayed();
    }
    // Check if task exists
    public boolean isTaskPresent(String taskName) {
        try {
            driver.findElement(By.xpath("//tbody//a[normalize-space()='" + taskName + "']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getDateValidationMessage() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class,'error') or contains(@class,'inline-error') or contains(@class,'message')]")
            ));

            return error.getText();

        } catch (Exception e) {
            return "";
        }
    }
    public boolean isTaskCreated() {
        try {
            // Locator for task rows (adjust as per your UI)
            List<WebElement> tasks = driver.findElements(By.xpath("//table//tr//td[contains(text(),'Demo Task')]"));

            return tasks.size() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    //search
    public void searchTask(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(searchBox));

        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
    }
    public boolean isTaskPresentInResults(String expectedTaskName) {
        WebElement selection=driver.findElement(By.xpath("//div[text()='Task']"));
        selection.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.visibilityOf(taskHeader));
            wait.until(ExpectedConditions.visibilityOfAllElements(taskResults));

            for (WebElement task : taskResults) {
                if (task.getText().trim().equalsIgnoreCase(expectedTaskName)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }
}