package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostHandlerTest {
    private PostHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        String publicFolderPath = "../cob_spec/public/";
        mockClientRequest = mock(ClientRequest.class);
        subject = new PostHandler(publicFolderPath, mockClientRequest);
    }

    @Test
    void postRequestBuildsAFullResponse() {
        String expectedStatus = "HTTP/1.1 200 OK";
        String expectedBody = "\"My\"=\"Data\"";
        String expectedHeaders = "11";

        when(mockClientRequest.getBody()).thenReturn(expectedBody);

        subject.handle();
        String status = subject.getStatus();
        String headers = subject.getHeaders().get("Content-Length");
        String body = subject.getBody();


        assertEquals(expectedStatus, status);
        assertEquals(expectedBody, body);
        assertEquals(expectedHeaders, headers);
    }
}