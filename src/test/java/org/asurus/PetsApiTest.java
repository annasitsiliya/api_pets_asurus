package org.asurus;

import io.restassured.response.Response;
import org.asurus.entities.Category;
import org.asurus.entities.Pet;
import org.asurus.entities.Tag;
import org.asurus.service.PetService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PetsApiTest {
    private PetService petService;
    private Pet pet;

    @BeforeClass
    public void init() {
        petService = new PetService();
        pet = createPet();
    }

    @BeforeMethod
    public void setup() {
        petService.sendPostRequest("pet", pet);
    }

    @Test
    public void getPet() {
        Response response = petService.sendGetRequest("pet/55");
        Pet actualPet = response.getBody().as(Pet.class);
        Assert.assertEquals(actualPet, pet);
    }

    @Test
    public void postPet() {
        Response response = petService.sendPostRequest("pet", pet);
        Pet actualPet = response.getBody().as(Pet.class);
        Assert.assertEquals(actualPet, pet);
    }

    @Test
    public void deletePet() {
        Response response = petService.sendDeleteRequest("pet/55");
        String actualBody = response.asString();
        Assert.assertTrue(actualBody.contains("55"), "Body doesn't contain pet id");
        Assert.assertTrue(actualBody.contains("200"), "Body doesn't contain OK status code");
    }

    private Pet createPet() {
        Category category = new Category().setId(43).setName("Taksa");
        Tag tag1 = new Tag().setId(12).setName("brown");
        Tag tag2 = new Tag().setId(12).setName("standard");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        return new Pet().setName("Betty")
                .setId(55)
                .setStatus("sold")
                .setCategory(category)
                .setTags(tags);
    }
}
