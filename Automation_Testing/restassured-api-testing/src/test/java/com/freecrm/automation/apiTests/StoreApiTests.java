package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Order;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.StoreDataBuilder;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreApiTests extends BaseTest {
    public StoreApiTests(){
        super(new TestContext());
    }

    @Test(priority = 1, description = "Get store inventory")
    @Epic("Store API Tests")
    @Feature("Inventory Management")
    @Story("Get Inventory and validate the response")
    public void getInventory() {
        Response response=getApiService().getPetInventory();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        int availableCount = response.jsonPath().getInt("available");
        Assert.assertTrue(availableCount >= 0, "Available count should be greater than or equal to 0");
    }

    @Test(priority = 2, description = "Place order")
    @Epic("Store API Tests")
    @Feature("Place order")
    @Story("Place order and validate the response")
    public void placeOrder() {
        long order_id= getConfigReader().getOrderId();
        long pet_id= getConfigReader().getPetId();
        Order newOrder = StoreDataBuilder.buildNewOrder(order_id,pet_id);

        IRestResponse<Order> response = getApiService().placeOrder(newOrder);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.isSuccessful(), "Expected successful response");
        if(response.isSuccessful()) {
            Order orderResponse = response.getBody();
            Assert.assertNotNull(orderResponse);
            Assert.assertEquals(order_id, orderResponse.getId());
            Assert.assertEquals(pet_id, orderResponse.getPetId());
            Assert.assertEquals(orderResponse.getQuantity(), 1);
            Assert.assertEquals(orderResponse.getStatus().toString(), "placed");
            Assert.assertFalse(orderResponse.isComplete());
        }
        else
        {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(errorResponse.getType(), "error");
        }
    }
    @Test(priority = 3, description = "Get order by ID")
    @Epic("Store API Tests")
    @Feature("Get order by ID")
    @Story("Get order by ID and validate the response")
    public void getOrderById() {
        long order_id= getConfigReader().getOrderId();
        IRestResponse<Order> response = getApiService().getOrder(order_id);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.isSuccessful(), "Expected successful response");
        if(response.isSuccessful()) {
            Order orderResponse = response.getBody();
            Assert.assertNotNull(orderResponse);
            Assert.assertEquals(order_id, orderResponse.getId());
            Assert.assertEquals(orderResponse.getPetId(), (int) getConfigReader().getPetId());
            Assert.assertEquals(orderResponse.getQuantity(), 1);
            Assert.assertEquals(orderResponse.getStatus().toString(), "placed");
            Assert.assertFalse(orderResponse.isComplete());
        }
        else
        {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(errorResponse.getType(), "error");
        }
    }

    @Test(priority = 4, description = "Delete order")
    @Epic("Store API Tests")
    @Feature("Delete order")
    @Story("Delete order and validate the response")
    public void deleteOrder() {
        long order_id= getConfigReader().getOrderId();
        IRestResponse<ApiResponse> response=getApiService().deleteOrder(order_id);

        System.out.println("Response: " + response.getContent());
        Assert.assertEquals(response.getBody().getType(),"unknown");
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
