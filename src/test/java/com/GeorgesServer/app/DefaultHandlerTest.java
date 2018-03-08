package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DefaultHandlerTest {
    private DefaultHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new DefaultHandler();
    }

    @Test
    void handlerCallsTheCorrectMethodsWhenBuildingARootResponse() {
        String expectedCode = "200";
        String expectedMsg = "OK";
        String expectedVersion = "HTTP/1.1";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
    }
}
