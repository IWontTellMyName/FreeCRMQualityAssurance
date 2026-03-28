package com.freecrm.automation.managers;

import com.freecrm.automation.pageObjects.DashboardPage;
import com.freecrm.automation.pageObjects.HomePage;
import com.freecrm.automation.pageObjects.LoginPage;
import com.freecrm.automation.pageObjects.company.ListViewPage;
import com.freecrm.automation.pageObjects.contacts.ContactPage;
import com.freecrm.automation.pageObjects.deals.DealsCreatePage;
import com.freecrm.automation.pageObjects.deals.DealsListPage;
import com.freecrm.automation.pageObjects.tasks.TasksPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
        private final WebDriver driver;
        private HomePage homePage;
        private LoginPage loginPage;
        private DashboardPage dashboardPage;
        private DealsListPage dealsListPage;
        private DealsCreatePage dealsCreatePage;
        private ListViewPage listViewPage;
        private ContactPage contactPage;
        private TasksPage tasksPage;


        public PageObjectManager(WebDriver driver) {
            this.driver = driver;
        }

        public HomePage getHomePage() {
            return (homePage == null) ? homePage = new HomePage(driver) : homePage;
        }

        public LoginPage getLoginPage() {
            return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
        }

        public DashboardPage getDashboardPage() {
            return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
        }

        public DealsListPage getDealsListPage() {
            return (dealsListPage == null) ? dealsListPage = new DealsListPage(driver) : dealsListPage;
        }

        public DealsCreatePage getDealsCreatePage() {
            return (dealsCreatePage == null) ? dealsCreatePage = new DealsCreatePage(driver) : dealsCreatePage;
        }

        public ListViewPage getListViewPage() {
            return (listViewPage == null) ? listViewPage = new ListViewPage(driver) : listViewPage;
        }

        public ContactPage getContactPage() {
            return (contactPage == null) ? contactPage = new ContactPage(driver) : contactPage;
        }

        public TasksPage getTasksPage() {
            return (tasksPage == null) ? tasksPage = new TasksPage(driver) : tasksPage;
        }

}
