package com.hfut.beike.controller;

import com.hfut.beike.common.R;
import com.hfut.beike.expection.ApiErrorCode;
import com.hfut.beike.expection.IErrorCode;
import org.junit.Before;
import org.junit.Test;

public class ApiControllerTest {

    private ApiController apiControllerUnderTest;

    @Before
    public void setUp() {
        apiControllerUnderTest = new ApiController() {
        };
    }

    @Test
    public void testSuccess() {
        // Setup
        // Run the test
        final R<String> result = apiControllerUnderTest.success("data");

        // Verify the results
    }

    @Test
    public void testFailed1() {
        // Setup
        // Run the test
        final R<String> result = apiControllerUnderTest.failed("msg");

        // Verify the results
    }

    @Test
    public void testFailed2() {
        // Setup
        final IErrorCode errorCode = ApiErrorCode.SUCCESS;

        // Run the test
        final R<String> result = apiControllerUnderTest.failed(errorCode);

        // Verify the results
    }
}
