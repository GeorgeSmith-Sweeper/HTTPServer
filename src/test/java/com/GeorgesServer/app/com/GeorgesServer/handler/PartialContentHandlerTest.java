package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PartialContentHandlerTest {
    private PartialContentHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new PartialContentHandler();
    }

    @Test
    void handlerBuildsA206Status() {
        String expectedCode = "206";
        String expectedMsg = "Partial Content";
        String expectedVersion = "HTTP/1.1";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
    }
}