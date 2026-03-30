package com.freecrm.automation.apiTests;

import com.freecrm.automation.apiEngine.IRestResponse;
import com.freecrm.automation.apiEngine.model.ApiResponse;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.apiEngine.model.Tag;
import com.freecrm.automation.context.TestContext;
import com.freecrm.automation.dataBuilder.PetDataBuilder;
import com.freecrm.automation.enums.Context;
import com.freecrm.automation.enums.PetStatus;
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


}
