package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.PetDataBuilder;
import com.freecrm.automation.enums.Context;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetApiTests extends BaseTest{

    public PetApiTests(){
        super(new TestContext());
    }

    @Test(priority = 1, description = "Create new pet — Bella the Golden Retriever")
    public void createPet() {
        long pet_id = getConfigReader().getPetId();
        Pet newPet = PetDataBuilder.buildNewPet(pet_id);

        IRestResponse<Pet> response = getApiService().createPet(newPet);

//        given()
//                .spec(requestSpec)
//                .body(newPet)
//                .when()
//                .post("/pet")
//                .then()
//                .spec(responseSpec)
//                .statusCode(200)
//                .body("id",            equalTo((int) PET_ID))
//                .body("name",          equalTo("Bella"))
//                .body("status",        equalTo("available"))
//                .body("category.name", equalTo("Dogs"))
//                .body("tags.name",     hasItems("golden-retriever", "friendly", "vaccinated"))
//                .body("photoUrls",     not(empty()))
//                .time(lessThan(ConfigManager.MAX_RESPONSE_TIME));

        Assert.assertTrue(response.isSuccessful(), "Expected successful response");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        if(response.isSuccessful())
        {
            Pet petResponse = response.getBody();
            Assert.assertNotNull(petResponse);
            Assert.assertEquals(pet_id, petResponse.getId());
            Assert.assertEquals(petResponse.getName(), newPet.getName());
            Assert.assertEquals(petResponse.getStatus(), newPet.getStatus());
            Assert.assertEquals(petResponse.getCategory().getName(), newPet.getCategory().getName());
            getScenarioContext().setContext(Context.PET_ID, petResponse.getId());
        }
        else
        {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(errorResponse.getType(), "error");
        }


    }


}
