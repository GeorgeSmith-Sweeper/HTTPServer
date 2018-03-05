package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterTest {
    private Router subject;
    private IHandler defaultHandler, formHandler;
    private ClientRequest mockedClientRequest;
    private HttpResponseBuilder responseBuilder;

    @BeforeEach
    private void setUp() {
        mockedClientRequest = mock(ClientRequest.class);
        responseBuilder = new HttpResponseBuilder();
        defaultHandler = new DefaultHandler(responseBuilder);
        formHandler = new FormHandler(responseBuilder);
        subject = new Router();
    }

    @Test
    void routeChoosesADefaultHandlerWhenTheUrlIsRoot() {
        when(mockedClientRequest.getUrl()).thenReturn("/");
        subject.addRoute("/", defaultHandler);

        IHandler result = subject.route(mockedClientRequest);

        assertTrue(result instanceof DefaultHandler);
    }

    @Test
    void routeChoosesAFormHandlerWhenTheUrlIsForm() {
        when(mockedClientRequest.getUrl()).thenReturn("/form");
        subject.addRoute("/form", formHandler);

        IHandler result = subject.route(mockedClientRequest);

        assertTrue(result instanceof FormHandler);
    }

}