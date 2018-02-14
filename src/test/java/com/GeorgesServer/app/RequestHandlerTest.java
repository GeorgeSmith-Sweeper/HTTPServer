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

}