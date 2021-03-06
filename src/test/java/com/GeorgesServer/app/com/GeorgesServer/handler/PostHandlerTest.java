package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostHandlerTest {
    private PostHandler subject;
    private ClientRequest mockClientRequest;
    private String filesPath = "../HTTPServer/src/test/";


    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        when(mockClientRequest.getUrl()).thenReturn("/form");
        when(mockClientRequest.getBody()).thenReturn("My=Data");
        subject = new PostHandler(filesPath, mockClientRequest);
    }

    @Test
    void postRequestBuildsAFullResponse() {
        String expectedStatus = StatusCodes.OK;

        subject.handle();
        String status = subject.getStatus();

        assertEquals(expectedStatus, status);
    }

    @Test
    void postRequestCreatesANewFileThatContainsTheBodyContent() throws Exception {
        String expectedBody = "My = Data";

        subject.handle();

        String body = new String(Files.readAllBytes(Paths.get("../HTTPServer/src/test/form")));

        assertEquals(expectedBody, body);
    }
}