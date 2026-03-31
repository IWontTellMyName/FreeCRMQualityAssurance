package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.User;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.UserDataBuilder;
import com.freecrm.automation.enums.Context;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSnehalApiTests extends BaseTest{

    public UserSnehalApiTests() {
        super(new TestContext());
    }

    // ─────────────────────────────────────────
    // TEST 1 — POST /user/createWithArray
    // ─────────────────────────────────────────
    @Test(priority = 1, description = "Create multiple users via array")
    @Epic("User API Tests by Snehal")
    @Feature("Create Multiple Users")
    @Story("Users wants to create multiple users via array")
    public void createUsersWithArray() {

        List<User> userList = UserDataBuilder.buildUserList();
        IRestResponse<ApiResponse> response = getApiService().createUsersWithArray(userList);
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ─────────────────────────────────────────
    // TEST 2 — POST /user/createWithList
    // ─────────────────────────────────────────
    @Test(priority = 2, description = "Create multiple users via list")
    @Epic("User API Tests by Snehal")
    @Feature("Create Multiple Users")
    @Story("Users wants to create multiple users via list")
    public void createUsersWithList() {

        List<User> userList = UserDataBuilder.buildUserList();
        IRestResponse<ApiResponse> response = getApiService().createUsersWithList(userList);
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ─────────────────────────────────────────
    // TEST 3 — GET /user/login
    // ─────────────────────────────────────────
    @Test(priority = 3, description = "Login user")
    @Epic("User API Tests by Snehal")
    @Feature("Authentication")
    @Story("User wants to login")
    public void loginUser() {
        getScenarioContext().setContext(Context.USERNAME, "Snehal");
        getScenarioContext().setContext(Context.PASSWORD, "Iem@2026");

        IRestResponse<ApiResponse> response = getApiService().login(
            getScenarioContext().getContext(Context.USERNAME).toString(),
            getScenarioContext().getContext(Context.PASSWORD).toString()
            );
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ─────────────────────────────────────────
    // TEST 4 — GET /user/logout
    // ─────────────────────────────────────────
    @Test(priority = 4, description = "Logout user")
    @Epic("User API Tests by Snehal")
    @Feature("Authentication")
    @Story("User wants to logout")
    public void logoutUser() {
        IRestResponse<ApiResponse> response = getApiService().logout();
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ─────────────────────────────────────────
    // TEST 5 — PUT /user/{username}
    // ─────────────────────────────────────────
    @Test(priority = 5, description = "Update user")
    @Epic("User API Tests by Snehal")
    @Feature("User Account Management")
    @Story("User wants to update profile")
    public void updateUser() {

        getScenarioContext().setContext(Context.PASSWORD, "Iem@2026");
        getScenarioContext().setContext(Context.EMAIL, "snehalghosh22@gmail.com");

        getScenarioContext().setContext(Context.FIRSTNAME, "Snehal");
        getScenarioContext().setContext(Context.LASTNAME, "Ghosh");
        getScenarioContext().setContext(Context.PHONE, "1478529630");


        User updatedUser = UserDataBuilder.buildUpdatedUser(
                getConfigReader().getUserId2(),
                getScenarioContext().getContext(Context.USERNAME).toString(),
                getScenarioContext().getContext(Context.FIRSTNAME).toString(),
                getScenarioContext().getContext(Context.LASTNAME).toString(),
                getScenarioContext().getContext(Context.EMAIL).toString(),
                getScenarioContext().getContext(Context.PASSWORD).toString(),
                getScenarioContext().getContext(Context.PHONE).toString()
        );
        IRestResponse<ApiResponse> response = getApiService().updateUser(getScenarioContext().getContext(Context.USERNAME).toString(), updatedUser);
        System.out.println("Response: " + response.getContent());
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    // ─────────────────────────────────────────
    // TEST 6 — DELETE /user/{username}
    // ─────────────────────────────────────────
    @Test(priority = 6, description = "Delete user")
    @Epic("User API Tests by Snehal")
    @Feature("User Account Management")
    @Story("User wants to delete account")
    public void deleteUser() {

        IRestResponse<ApiResponse> response = getApiService().deleteUser(getScenarioContext().getContext(Context.USERNAME).toString());
        if(response.isSuccessful())
        {
            System.out.println("Response: " + response.getContent());
            Assert.assertEquals(response.getBody().getType(), "unknown");
            Assert.assertEquals(response.getStatusCode(), 200);
        }
        else if(response.getContent().isEmpty())
        {
            Assert.assertTrue(response.getContent().isEmpty());
            System.out.println("User doesn't exists");
        }
    }
}
