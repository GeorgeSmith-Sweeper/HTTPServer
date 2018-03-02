package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterTest {
    private Router subject;
    private IHandler defaultHandler, formHandler;
    private HttpResponseBuilder responseBuilder;
    private ClientRequest mockedClientRequest;

    @BeforeEach
    private void setUp() {
        responseBuilder = new HttpResponseBuilder();
        defaultHandler = new DefaultHandler(responseBuilder);
        formHandler = new FormHandler(responseBuilder);
        mockedClientRequest = mock(ClientRequest.class);
        subject = new Router(defaultHandler, formHandler);

    }

    @Test
    void routeChoosesADefaultHandlerWhenTheUrlIsRoot() {
        when(mockedClientRequest.getMethod()).thenReturn("GET");
        when(mockedClientRequest.getUrl()).thenReturn("/");


        IHandler result = subject.route(mockedClientRequest);

        assertTrue(result instanceof DefaultHandler);
    }

    @Test
    void routeChoosesAFormHandlerWhenTheUrlIsForm() {
        when(mockedClientRequest.getMethod()).thenReturn("");
        when(mockedClientRequest.getUrl()).thenReturn("/form");

        IHandler result = subject.route(mockedClientRequest);

        assertTrue(result instanceof FormHandler);
    }
}