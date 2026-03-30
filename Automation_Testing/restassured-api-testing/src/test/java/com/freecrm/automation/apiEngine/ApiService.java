package com.freecrm.automation.apiEngine;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

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


}
