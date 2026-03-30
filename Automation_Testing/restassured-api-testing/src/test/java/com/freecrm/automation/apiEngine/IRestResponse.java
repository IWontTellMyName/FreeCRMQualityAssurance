package com.freecrm.automation.apiEngine;

import com.freecrm.automation.apiEngine.model.ApiResponse;
import io.restassured.response.Response;

public interface IRestResponse <T>{
    T getBody();

    ApiResponse getErrorBody();

    String getContent();

    int getStatusCode();

    boolean isSuccessful();

    String getStatusDescription();

    Response getResponse();

    Exception getException();
}
