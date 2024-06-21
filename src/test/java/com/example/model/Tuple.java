package com.example.model;

import com.example.base.TestBot;
import com.example.engine.enums.Response;

import java.lang.reflect.Method;

/**
 * A Tuple is a test move combined along with the expected response
 */
public class Tuple {

    private final TestBot testBot;

    private final Method method;

    private final Object[] params;

    private final Response expectedResponse;

    public Tuple(Response expectedResponse, TestBot testBot, Method method, Object... params) {
        this.testBot = testBot;
        this.expectedResponse = expectedResponse;
        this.method = method;
        this.params = params;
    }

    public TestBot getTestBot() {
        return testBot;
    }

    public Response getExpectedResponse() {
        return expectedResponse;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }
}
