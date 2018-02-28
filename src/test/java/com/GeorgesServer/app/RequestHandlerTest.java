package com.GeorgesServer.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestHandlerTest {
    private RequestHandler subject;
    private ClientRequest mockClientRequest;
    private String OK_STATUS = "HTTP/1.1 " + StatusCodes.OK +  "\n";

    @BeforeEach
    public void setUp() {
        subject = new RequestHandler();
        mockClientRequest = mock(ClientRequest.class);
    }

    @Test
    void handlerFormatsAResponseWithABasicGetRequest() {
        when(mockClientRequest.getUrl()).thenReturn("/");
        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");

        String result = subject.handle(mockClientRequest);

        assertEquals(OK_STATUS, result);
    }

    @Test
    void handlerFormatsAResponseWithAPostRequestWithNoBody() {
        when(mockClientRequest.getUrl()).thenReturn("/form");
        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");
        when(mockClientRequest.getMethod()).thenReturn("POST");

        String result = subject.handle(mockClientRequest);

        assertEquals(OK_STATUS, result);
    }

    @Test
    void handlerFormatsAResponseWithAHeadRequestWithNoBody() {
        when(mockClientRequest.getMethod()).thenReturn("HEAD");
        when(mockClientRequest.getUrl()).thenReturn("/foobar");
        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");

        String expectedResponse = "HTTP/1.1 " + StatusCodes.NOT_FOUND + "\n";
        String result = subject.handle(mockClientRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerFormatsAResponseWithPutRequestWithNoBody() {
        when(mockClientRequest.getMethod()).thenReturn("PUT");
        when(mockClientRequest.getUrl()).thenReturn("/form");
        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");


        String result = subject.handle(mockClientRequest);

        assertEquals(OK_STATUS, result);
    }
}