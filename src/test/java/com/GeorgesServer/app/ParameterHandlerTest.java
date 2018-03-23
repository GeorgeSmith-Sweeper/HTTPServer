package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParameterHandlerTest {
    private ParameterHandler subject;
    private ClientRequest mockedClientRequest;
    private String publicFolderPath = "../cob_spec/public/";


    @BeforeEach
    private void setUp() {
        mockedClientRequest = mock(ClientRequest.class);
        subject = new ParameterHandler(publicFolderPath, mockedClientRequest);
    }

    @Test
    void getQueriesCanParseIncomingQueries() {
        String firstQuery = "car=jaguar";
        String secondQuery = "cool=cat";
        when(mockedClientRequest.getUrl()).thenReturn("/hello?car=jaguar&cool=cat");

        subject.handle();
        String[] result = subject.getQueries();

        assertEquals(firstQuery, result[0]);
        assertEquals(secondQuery, result[1]);
    }

    @Test
    void parameterParserAssignsValuesToKeysFromTheQuery() {
        when(mockedClientRequest.getUrl()).thenReturn("/hello?car=jaguar");

        subject.handle();

        HashMap<String, String> result = subject.getParameters();

        assertEquals("jaguar", result.get("car"));
    }

    @Test
    void parameterDecoderDecodesURLEncodedParameters() {
        String expectedValue = "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
        when(mockedClientRequest.getUrl()).thenReturn("/hello?car=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F");

        subject.handle();

        HashMap<String, String> result = subject.getParameters();

        assertEquals(expectedValue, result.get("car"));
    }

}