package org.asurus.service.uritemplate;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.asurus.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PetService {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";

    private final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public PetService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = RestAssured.given();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        requestSpecification.headers(headers);
    }


    public Response sendGetRequest(String uri) {
        Log.info("Sending Get request to the URL " + uri);
        Response response = requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
                .when().get(prepareUri.apply(uri));
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    public Response sendPostRequest(String uri, Object body) {
        return requestSpecification.body(body).expect().statusCode(HttpStatus.SC_CREATED).log().ifError()
                .when().post(prepareUri.apply(uri));
    }

    public void sendDeleteRequest(String uri) {
        requestSpecification.expect().statusCode(HttpStatus.SC_NO_CONTENT).log().ifError()
                .when().delete(prepareUri.apply(uri));
    }
}
