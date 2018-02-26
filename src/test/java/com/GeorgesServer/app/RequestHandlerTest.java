package com.GeorgesServer.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RequestHandlerTest {
    private RequestHandler subject;
    private HashMap<String, String> clientRequest = new HashMap<>();

    @BeforeEach
    public void setUp() {
        subject = new RequestHandler();
    }

    @AfterEach
    public void clearClientRequest() {
        clientRequest.clear();
    }

    @Test
    void handlerFormatsAResponseWithABasicGetRequest() {
        clientRequest.put("Method", "GET");
        clientRequest.put("Url", "/");
        clientRequest.put("HttpVersion", "HTTP/1.1");

        String expectedResponse = "HTTP/1.1 200 OK\n";
        String result = subject.handle(clientRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerFormatsAResponseWithAPostRequestWithNoBody() {
        clientRequest.put("Method", "POST");
        clientRequest.put("Url", "/form");
        clientRequest.put("HttpVersion", "HTTP/1.1");
        clientRequest.put("Content-Type", "application/x-www-form-urlencoded");
        clientRequest.put("My", "Data");

        String expectedResponse = "HTTP/1.1 200 OK\n";
        String result = subject.handle(clientRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerFormatsAResponseWithAHeadRequestWithNoBody() {
        clientRequest.put("Method", "HEAD");
        clientRequest.put("Url", "/foobar");
        clientRequest.put("HttpVersion", "HTTP/1.1");

        String expectedResponse = "HTTP/1.1 404 NOT FOUND\n";
        String result = subject.handle(clientRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerFormatsAResponseWithPutRequestWithNoBody() {
        clientRequest.put("Method", "PUT");
        clientRequest.put("Url", "/form");
        clientRequest.put("HttpVersion", "HTTP/1.1");

        String expectedResponse = "HTTP/1.1 200 OK\n";
        String result = subject.handle(clientRequest);

        assertEquals(expectedResponse, result);
    }
}