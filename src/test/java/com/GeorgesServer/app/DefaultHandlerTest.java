package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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