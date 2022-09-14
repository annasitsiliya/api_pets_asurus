package org.asurus;

import io.restassured.response.Response;
import org.asurus.service.uritemplate.PetService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetsApiTest {

    @Test
    public void getPet (){
        PetService petService = new PetService();
      Response response = petService.sendGetRequest("pet/1");
        Assert.assertEquals(response.statusCode(), 200);

    }


}
