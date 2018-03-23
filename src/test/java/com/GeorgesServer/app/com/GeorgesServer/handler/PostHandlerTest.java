package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.handler.PostHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        subject = new PostHandler(filesPath, mockClientRequest);
    }

    @Test
    void postRequestBuildsAFullResponse() {
        String expectedStatus = "HTTP/1.1 200 OK";

        subject.handle();
        String status = subject.getStatus();


        assertEquals(expectedStatus, status);
    }
}