package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        String expectedResponse = "HTTP/1.1 200 OK\n";

        subject.handle();
        String result = subject.format();

        assertEquals(expectedResponse, result);
    }
}