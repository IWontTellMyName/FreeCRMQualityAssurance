package com.freecrm.automation.apiEngine;

import com.freecrm.automation.exceptions.DeserializationException;
import io.restassured.response.Response;

public class RestResponse<T> implements IRestResponse<T> {

    private final Class<T> responseType;
    private final Response response;
    private Exception exception;

    public RestResponse(Class<T> responseType, Response response) {
        this.responseType = responseType;
        this.response = response;
    }

    @Override
    public T getBody() {
        try {
            return response.getBody().as(responseType);
        } catch (Exception e) {
            this.exception = e;
            throw new DeserializationException("Failed to deserialize response body to " + responseType.getSimpleName(), e);
        }
    }

    @Override
    public String getContent() {
        return response.getBody().asString();
    }

    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Override
    public boolean isSuccessful() {
        int statusCode = response.getStatusCode();
        return statusCode >= 200 && statusCode < 400;
    }

    @Override
    public String getStatusDescription() {
        return response.getStatusLine();
    }

    @Override
    public Response getResponse() {
        return response;
    }

    @Override
    public Exception getException() {
        return exception;
    }
}
