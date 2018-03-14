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
        String expectedResponse = "HTTP/1.1 200 OK\n";

        subject.handle(mockClientRequest);
        String result = subject.format();

        assertEquals(expectedResponse, result);
    }
}

