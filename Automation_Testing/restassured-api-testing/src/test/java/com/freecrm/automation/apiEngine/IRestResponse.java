package com.freecrm.automation.apiEngine;

import io.restassured.response.Response;

public interface IRestResponse <T>{
    T getBody();

    String getContent();

    int getStatusCode();

    boolean isSuccessful();

    String getStatusDescription();

    Response getResponse();

    Exception getException();
}
