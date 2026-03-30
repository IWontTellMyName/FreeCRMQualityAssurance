package com.freecrm.automation.apiEngine;

import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Order;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.apiEngine.model.User;
import com.freecrm.automation.enums.PetStatus;
import com.freecrm.automation.utils.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiService {
    private final String baseUrl;

    public ApiService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RequestSpecification requestSpec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .relaxedHTTPSValidation();
    }

    public IRestResponse<Pet> createPet(Pet pet) {
        Response res = requestSpec()
                .body(pet)
                .when()
                .post(Routes.PET_CREATE);
        return new RestResponse<>(Pet.class, res);
    }

    public IRestResponse<Pet> updatePet(Pet updatedPet) {
        Response res = requestSpec()
                .body(updatedPet)
                .when()
                .put(Routes.PET_UPDATE);
        return new RestResponse<>(Pet.class, res);
    }

    public RestResponse<Pet[]> findPetByStatus(PetStatus petStatus) {
        Response res = requestSpec()
                .queryParam("status", petStatus.name().toLowerCase())
                .when()
                .get(Routes.PET_FIND_BY_STATUS);

        return new RestResponse<>(Pet[].class, res);
    }

    public IRestResponse<Pet> findPetById(long petId) {
        Response res = requestSpec()
                .pathParam("petId", petId)
                .when()
                .get(Routes.PET_BY_ID);
        return new RestResponse<>(Pet.class, res);
    }

    public IRestResponse<ApiResponse> deletePet(long petId) {
        Response res = requestSpec()
                .pathParam("petId", petId)
                .header("api_key", ConfigReader.getInstance().getApiKey())
                .when()
                .delete(Routes.PET_DELETE);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public Response getPetInventory()
    {
        Response res = requestSpec()
                .when()
                .get(Routes.STORE_INVENTORY);
        return res;
    }

    public IRestResponse<Order> placeOrder(Order order) {
        Response res = requestSpec()
                .body(order)
                .when()
                .post(Routes.STORE_ORDER_PLACE);
        return new RestResponse<>(Order.class, res);
    }

    public IRestResponse<Order> getOrder(long orderId) {
        Response res = requestSpec()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.STORE_ORDER_BY_ID);
        return new RestResponse<>(Order.class, res);
    }

    public IRestResponse<ApiResponse> deleteOrder(long orderId) {
        Response res = requestSpec()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.STORE_ORDER_DELETE);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public IRestResponse<ApiResponse> createUsersWithList(List<User> users) {
        Response res = requestSpec()
                .body(users)
                .when()
                .post(Routes.USER_CREATE_WITH_LIST);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public IRestResponse<User> getUserByUserName(String userName) {
        Response res = requestSpec()
                .pathParam("username", userName)
                .when()
                .get(Routes.USER_BY_USERNAME);
        return new RestResponse<>(User.class, res);
    }

    public IRestResponse<ApiResponse> updateUser(String userName, User updatedUser)
    {
        Response res = requestSpec()
                .body(updatedUser)
                .pathParam("username", userName)
                .when()
                .put(Routes.USER_UPDATE_BY_USERNAME);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public IRestResponse<ApiResponse> deleteUser(String userName)
    {
        Response res = requestSpec()
                .pathParam("username", userName)
                .when()
                .delete(Routes.USER_DELETE_BY_USERNAME);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public IRestResponse<ApiResponse> createUsersWithArray(List<User> users) {
        Response res = requestSpec()
                .body(users)
                .when()
                .post(Routes.USER_CREATE_WITH_ARRAY);
        return new RestResponse<>(ApiResponse.class, res);
    }

    public IRestResponse<ApiResponse> createUser(User user) {
        Response res = requestSpec()
                .body(user)
                .when()
                .post(Routes.USER_CREATE);
        return new RestResponse<>(ApiResponse.class, res);
    }


}
