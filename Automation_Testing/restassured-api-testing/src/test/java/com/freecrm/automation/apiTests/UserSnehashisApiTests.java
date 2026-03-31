package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.User;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.UserDataBuilder;
import com.freecrm.automation.enums.Context;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSnehashisApiTests extends BaseTest{

    public UserSnehashisApiTests() {
        super(new TestContext());
    }

    @Test(priority = 1, description = "Create single user")
    public void createUser() {
        long userId = getConfigReader().getUserId1();
        getScenarioContext().setContext(Context.USERNAME, "Snehashis");
        getScenarioContext().setContext(Context.FIRSTNAME, "Snehashis");
        getScenarioContext().setContext(Context.LASTNAME, "Mandal");
        getScenarioContext().setContext(Context.EMAIL, "snehashismandal@gmail.com");
        getScenarioContext().setContext(Context.PASSWORD, "snehashismandal");
        getScenarioContext().setContext(Context.PHONE, "1478529630");

        User newUser = UserDataBuilder.buildNewUser(
                userId,
                getScenarioContext().getContext(Context.USERNAME).toString(),
                getScenarioContext().getContext(Context.FIRSTNAME).toString(),
                getScenarioContext().getContext(Context.LASTNAME).toString(),
                getScenarioContext().getContext(Context.EMAIL).toString(),
                getScenarioContext().getContext(Context.PASSWORD).toString(),
                getScenarioContext().getContext(Context.PHONE).toString()
        );

        IRestResponse<ApiResponse> response = getApiService().createUser(newUser);
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    // ─────────────────────────────────────────
    // TEST 2 — GET /user/{username}
    // ─────────────────────────────────────────
    @Test(priority = 2, description = "Get user by username")
    public void testGetUserByUsername() {
        String userName = getScenarioContext().getContext(Context.USERNAME).toString();
        IRestResponse<User> response = getApiService().getUserByUserName(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
        User userResponse = response.getBody();
        Assert.assertEquals(userResponse.getId(), getConfigReader().getUserId1());
        Assert.assertEquals(userResponse.getUsername(), getScenarioContext().getContext(Context.USERNAME).toString());
        Assert.assertEquals(userResponse.getFirstName(), getScenarioContext().getContext(Context.FIRSTNAME).toString());
        Assert.assertEquals(userResponse.getLastName(), getScenarioContext().getContext(Context.LASTNAME).toString());
        Assert.assertEquals(userResponse.getEmail(), getScenarioContext().getContext(Context.EMAIL).toString());
        Assert.assertEquals(userResponse.getPassword(), getScenarioContext().getContext(Context.PASSWORD).toString());
        Assert.assertEquals(userResponse.getPhone(), getScenarioContext().getContext(Context.PHONE).toString());

    }

    // ─────────────────────────────────────────
    // TEST 3 — PUT /user/{username}
    // ─────────────────────────────────────────
    @Test(priority = 3, description = "Update user")
    public void updateUser() {

        getScenarioContext().setContext(Context.LASTNAME, "Mandal-Updated");
        getScenarioContext().setContext(Context.EMAIL, "snehashisupdated@gmail.com");
        User updatedUser = UserDataBuilder.buildUpdatedUser(
                getConfigReader().getUserId1(),
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
    // TEST 4 — DELETE /user/{username}
    // ─────────────────────────────────────────
    @Test(priority = 4, description = "Delete user")
    public void deleteUser() {

        IRestResponse<ApiResponse> response = getApiService().deleteUser(getScenarioContext().getContext(Context.USERNAME).toString());
        System.out.println("Response: " + response.getContent());
        Assert.assertEquals(response.getBody().getType(), "unknown");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
