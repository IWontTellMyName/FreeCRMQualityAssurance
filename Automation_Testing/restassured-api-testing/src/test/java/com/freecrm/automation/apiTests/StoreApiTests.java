package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Order;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.PetDataBuilder;
import com.freecrm.automation.dataBuilder.StoreDataBuilder;
import com.freecrm.automation.enums.Context;
import io.restassured.response.Response;
import org.jsoup.Connection;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreApiTests extends BaseTest {
    public StoreApiTests(){
        super(new TestContext());
    }

    @Test(priority = 1, description = "Get store inventory — verify pet counts by status")
    public void getInventory() {
        Response response=getApiService().getPetInventory();

//        given()
//                .spec(requestSpec)
//                .when()
//                .get("/store/inventory")
//                .then()
//                .spec(responseSpec)
//                .statusCode(200)
//                .body("$",         notNullValue())              // Must return an object
//                .body("available", notNullValue())              // Must have available count
//                .body("available", greaterThanOrEqualTo(0))
//                .time(lessThan(ConfigManager.MAX_RESPONSE_TIME));

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        int availableCount = response.jsonPath().getInt("available");
        Assert.assertTrue(availableCount >= 0, "Available count should be greater than or equal to 0");
    }

    @Test(priority = 2, description = "Place order — Emily orders Bella the dog for adoption")
    public void placeOrder() {
        long order_id= getConfigReader().getOrderId();
        long pet_id= getConfigReader().getPetId();
        Order newOrder = StoreDataBuilder.buildNewOrder(order_id,pet_id);

        IRestResponse<Order> response = getApiService().placeOrder(newOrder);

//        given()
//                .spec(requestSpec)
//                .body(newOrder)
//                .when()
//                .post("/store/order")
//                .then()
//                .spec(responseSpec)
//                .statusCode(200)
//                .body("id",       equalTo(ORDER_ID))
//                .body("petId",    equalTo((int) PET_ID))
//                .body("quantity", equalTo(1))
//                .body("status",   equalTo("placed"))
//                .body("complete", equalTo(false))
//                .body("shipDate", notNullValue())
//                .time(lessThan(ConfigManager.MAX_RESPONSE_TIME));
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
    @Test(priority = 3, description = "Get order by ID — verify Emily's order details")
    public void getOrderById() {
        long order_id= getConfigReader().getOrderId();
        IRestResponse<Order> response = getApiService().getOrder(order_id);

//        given()
//                .spec(requestSpec)
//                .pathParam("orderId", ORDER_ID)
//                .when()
//                .get("/store/order/{orderId}")
//                .then()
//                .spec(responseSpec)
//                .statusCode(200)
//                .body("id",       equalTo(ORDER_ID))
//                .body("petId",    equalTo((int) PET_ID))
//                .body("quantity", equalTo(1))
//                .body("status",   equalTo("placed"))
//                .body("complete", equalTo(false))
//                .time(lessThan(ConfigManager.MAX_RESPONSE_TIME));


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

    @Test(priority = 4, description = "Delete order — Emily cancels her order, confirm 404")
    public void deleteOrder() {
        long order_id= getConfigReader().getOrderId();
        IRestResponse<ApiResponse> response=getApiService().deleteOrder(order_id);

        // Step A — Cancel order
//        given()
//                .spec(requestSpec)
//                .pathParam("orderId", ORDER_ID)
//                .when()
//                .delete("/store/order/{orderId}")
//                .then()
//                .spec(responseSpec)
//                .statusCode(200)
//                .time(lessThan(ConfigManager.MAX_RESPONSE_TIME));
//
//        // Step B — Confirm cancellation with 404
//        given()
//                .spec(requestSpec)
//                .pathParam("orderId", ORDER_ID)
//                .when()
//                .get("/store/order/{orderId}")
//                .then()
//                .spec(responseSpec)
//                .statusCode(404);
        System.out.println("Response: " + response.getContent());
        Assert.assertEquals(response.getBody().getType(),"unknown");

        Assert.assertEquals(response.getStatusCode(),404);

    }

}
