package com.freecrm.automation.dataBuilder;

import com.freecrm.automation.apiEngine.model.Category;
import com.freecrm.automation.apiEngine.model.Pet;
import com.freecrm.automation.apiEngine.model.Tag;
import com.freecrm.automation.enums.PetStatus;

import java.util.Arrays;

public class PetDataBuilder {

    private PetDataBuilder() {}

    public static Pet buildNewPet(long petId) {
        return Pet.builder()
                .id(petId)
                .category(Category.builder().id(1).name("Dogs").build())
                .name("Bella")
                .photoUrls(Arrays.asList(
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/YellowLabradorLooking_new.jpg/320px-YellowLabradorLooking_new.jpg"
                ))
                .tags(Arrays.asList(
                        Tag.builder().id(1).name("golden-retriever").build(),
                        Tag.builder().id(2).name("friendly").build(),
                        Tag.builder().id(3).name("vaccinated").build()
                ))
                .status(PetStatus.available)
                .build();
    }

    public static Pet buildUpdatedPet(long  petId) {
        return Pet.builder()
                .id(petId)
                .category(Category.builder().id(1).name("Dogs").build())
                .name("Bella — Adopted")
                .photoUrls(Arrays.asList(
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/YellowLabradorLooking_new.jpg/320px-YellowLabradorLooking_new.jpg",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Labrador_on_Quantock_%282175262184%29.jpg/320px-Labrador_on_Quantock_%282175262184%29.jpg"
                ))
                .tags(Arrays.asList(
                        Tag.builder().id(1).name("golden-retriever").build(),
                        Tag.builder().id(2).name("friendly").build(),
                        Tag.builder().id(4).name("adopted").build()
                ))
                .status(PetStatus.sold)
                .build();
    }
}
