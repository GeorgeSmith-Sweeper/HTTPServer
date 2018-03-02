package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RequestHandlerTest {
    private DefaultHandler defaultHandler;
    private ClientRequest mockClientRequest;
    private HttpResponseBuilder mockResponseBuilder;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        mockResponseBuilder = mock(HttpResponseBuilder.class);
        defaultHandler = new DefaultHandler(mockResponseBuilder);
    }

    @Test
    void handlerCallsTheCorrectMethodsWhenBuildingARootResponse() {

        defaultHandler.handle(mockClientRequest);

        verify(mockResponseBuilder).buildHttpVersion();
        verify(mockResponseBuilder).buildOkStatus();
        verify(mockResponseBuilder).getResponse();
    }
}