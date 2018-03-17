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
    void postRequestsRespondWithA200StatusCode() {
        String expectedResponse = "HTTP/1.1 200 OK";

        subject.handle();
        String result = subject.getStatus();

        assertEquals(expectedResponse, result);
    }

    @Test
    void postRequestSetsTheBodyOfResponse() {
        String body = "\"My\"=\"Data\"";
        when(mockClientRequest.getBody()).thenReturn(body);
        subject.handle();

        String result = subject.getBody();

        assertEquals(body, result);
    }

    @Test
    void postRequestSetsContentLenghToTheLengthOfTheBody() {
        String body = "\"My\"=\"Data\"";
        when(mockClientRequest.getBody()).thenReturn(body);
        subject.handle();

        String result = subject.getHeaders().get("Content-Length");

        assertEquals("11", result);
    }
}