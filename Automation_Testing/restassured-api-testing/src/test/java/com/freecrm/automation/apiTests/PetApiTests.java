package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.apiEngine.model.Tag;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.PetDataBuilder;
import com.freecrm.automation.enums.Context;
import com.freecrm.automation.enums.PetStatus;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PetApiTests extends BaseTest{

    public PetApiTests(){
        super(new TestContext());
    }

    @Test(priority = 1, description = "Create new pet")
    @Epic("Pet API Tests")
    @Feature("Create Pet")
    @Story("Create a new pet and validate the response")
    public void testCreatePet() {
        long pet_id = getConfigReader().getPetId();
        Pet newPet = PetDataBuilder.buildNewPet(pet_id);

        IRestResponse<Pet> response = getApiService().createPet(newPet);

        if(response.isSuccessful())
        {
            Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

            Pet petResponse = response.getBody();
            Assert.assertNotNull(petResponse);

            Assert.assertEquals(petResponse.getId(), newPet.getId());
            Assert.assertEquals(petResponse.getName(), newPet.getName());
            Assert.assertEquals(petResponse.getStatus(), newPet.getStatus());
            Assert.assertEquals(petResponse.getCategory().getName(), newPet.getCategory().getName());

            List<String> tagNames = petResponse.getTags()
                    .stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList());

            Assert.assertTrue(tagNames.contains("golden-retriever"));
            Assert.assertTrue(tagNames.contains("friendly"));
            Assert.assertTrue(tagNames.contains("vaccinated"));

            Assert.assertNotNull(petResponse.getPhotoUrls());
            Assert.assertFalse(petResponse.getPhotoUrls().isEmpty());

            getScenarioContext().setContext(Context.PET_ID, petResponse.getId());
            getScenarioContext().setContext(Context.PET, petResponse);
        }
        else
        {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(errorResponse.getType(), "error");
        }

    }

    @Test(priority = 2, description = "Get pet by ID")
    @Epic("Pet API Tests")
    @Feature("Find Pet By Id")
    @Story("Retrieve the created pet by ID and validate the response")
    public void testGetPetById() {

        long PET_ID = (long) getScenarioContext().getContext(Context.PET_ID);
        IRestResponse<Pet> response = getApiService().findPetById(PET_ID);

        Pet createdPet = (Pet) getScenarioContext().getContext(Context.PET);

        if(response.isSuccessful())
        {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Expected status code 200");

            Pet petResponse = response.getBody();
            Assert.assertNotNull(petResponse);

            Assert.assertEquals(petResponse.getId(), createdPet.getId());
            Assert.assertEquals(petResponse.getName(), createdPet.getName());
            Assert.assertEquals(petResponse.getStatus(), createdPet.getStatus());

            Assert.assertNotNull(petResponse.getCategory());
            Assert.assertEquals(petResponse.getCategory().getId(), createdPet.getCategory().getId());
            Assert.assertEquals(petResponse.getCategory().getName(), createdPet.getCategory().getName());

            Assert.assertNotNull(petResponse.getPhotoUrls());
            Assert.assertEquals(petResponse.getPhotoUrls().size(), createdPet.getPhotoUrls().size());

            Assert.assertNotNull(petResponse.getTags());
            Assert.assertEquals(petResponse.getTags().size(), createdPet.getTags().size());
        }
        else {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Expected status code 404");
            Assert.assertEquals(errorResponse.getType(), "error");
            Assert.assertEquals(errorResponse.getMessage(), "Pet not found");
        }

    }

    @Test(priority = 3, description = "Find pets by status")
    @Epic("Pet API Tests")
    @Feature("Find Pets by Status")
    @Story("Find pets by status 'available' and validate the response")
    public void testFindPetByStatus() {
        IRestResponse<Pet[]> response = getApiService().findPetByStatus(PetStatus.available);
        long petId = (long) getScenarioContext().getContext(Context.PET_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Expected status code 200");
        Assert.assertTrue(response.isSuccessful(), "Expected successful response");

        Pet[] petsArray = response.getBody();
        Assert.assertNotNull(petsArray, "Response body is null");

        List<Pet> petList = Arrays.asList(petsArray);
        Assert.assertFalse(petList.isEmpty(), "Expected at least one pet with status 'available'");

        Assert.assertTrue(
                petList.stream().allMatch(p -> p.getStatus() == PetStatus.available),
                "Not all pets have status 'available'"
        );

        Assert.assertTrue(
                petList.stream().anyMatch(p -> p.getId() == petId),
                "Bella (ID: " + petId + ") not found in results!"
        );

    }

    @Test(priority = 4, description = "Update pet")
    @Epic("Pet API Tests")
    @Feature("Update Pet")
    @Story("Update the existing pet's name, status, and tags, then validate the response")
    public void testUpdatePet() {
        long PET_ID = (long) getScenarioContext().getContext(Context.PET_ID);
        Pet updatedPet = PetDataBuilder.buildUpdatedPet(PET_ID);
        Pet oldPet = (Pet) getScenarioContext().getContext(Context.PET);

        IRestResponse<Pet> response = getApiService().updatePet(updatedPet);
        if(response.isSuccessful())
        {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Expected status code 200");
            Pet petResponse = response.getBody();
            Assert.assertNotNull(petResponse);

            Assert.assertEquals(petResponse.getId(), updatedPet.getId());
            Assert.assertEquals(petResponse.getName(), updatedPet.getName());
            Assert.assertEquals(petResponse.getStatus(), updatedPet.getStatus());

            Assert.assertNotNull(petResponse.getCategory());
            Assert.assertEquals(petResponse.getCategory().getId(), updatedPet.getCategory().getId());
            Assert.assertEquals(petResponse.getCategory().getName(), updatedPet.getCategory().getName());

            Assert.assertNotNull(petResponse.getPhotoUrls());
            Assert.assertEquals(petResponse.getPhotoUrls().size(), 2);

            Assert.assertNotNull(petResponse.getTags());
            Assert.assertEquals(petResponse.getTags().size(), updatedPet.getTags().size());

            List<String> tagNames = petResponse.getTags()
                    .stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList());

            Assert.assertTrue(tagNames.contains("golden-retriever"));
            Assert.assertTrue(tagNames.contains("friendly"));
            Assert.assertTrue(tagNames.contains("adopted"));

            //Test against old Dog
            Assert.assertNotEquals(petResponse.getName(), oldPet.getName());
            Assert.assertNotEquals(petResponse.getStatus(), oldPet.getStatus());
            Assert.assertFalse(tagNames.contains("vaccinated"));

            getScenarioContext().setContext(Context.PET, updatedPet);

            //Status check after update
            IRestResponse<Pet[]> res = getApiService().findPetByStatus(PetStatus.available);
            Pet[] petsArray = res.getBody();
            Assert.assertNotNull(petsArray);

            List<Pet> petList = Arrays.asList(petsArray);
            Assert.assertFalse(petList.isEmpty(), "Expected at least one pet with status 'available'");

            Assert.assertFalse(
                    petList.stream().anyMatch(p -> p.getId() == PET_ID),
                    "Bella (ID: " + PET_ID + ") should not be found in results!"
            );

        }
        else
        {
            ApiResponse errorResponse = response.getErrorBody();
            Assert.assertEquals(errorResponse.getType(), "error");
        }
    }

    @Test(priority = 5, description = "Delete pet")
    @Epic("Pet API Tests")
    @Feature("Delete Pet")
    @Story("Delete the existing pet and validate the response, then verify deletion by attempting to retrieve the pet")
    public void deletePet() {
        long  PET_ID = (long) getScenarioContext().getContext(Context.PET_ID);
        IRestResponse<ApiResponse> response = getApiService().deletePet(PET_ID);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Expected status code 200");
        Assert.assertTrue(response.isSuccessful(), "Expected successful response");

        ApiResponse apiResponse = response.getBody();
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(apiResponse.getType(), "unknown");
        Assert.assertEquals(apiResponse.getMessage(), String.valueOf(PET_ID));
        Assert.assertEquals(apiResponse.getCode(), 200);

        //Validate Deletion
        IRestResponse<Pet> res = getApiService().findPetById(PET_ID);
        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Expected status code 404");
        Assert.assertFalse(res.isSuccessful(), "Expected unsuccessful response");
        Assert.assertEquals(res.getErrorBody().getType(), "error");
        Assert.assertEquals(res.getErrorBody().getMessage(), "Pet not found");

        //Validate Re-deletion after deletion
        IRestResponse<ApiResponse> deleteResponse =  getApiService().deletePet(PET_ID);
        Assert.assertEquals(deleteResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Expected status code 404");
        Assert.assertFalse(deleteResponse.isSuccessful(), "Expected unsuccessful response");
        Assert.assertTrue(deleteResponse.getContent().isEmpty());

    }


}
