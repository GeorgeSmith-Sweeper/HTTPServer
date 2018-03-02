package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RouterTest {
    private Router subject;
    private IHandler defaultHandler, formHandler;
    private HttpResponseBuilder responseBuilder;

    @BeforeEach
    private void setUp() {
        responseBuilder = new HttpResponseBuilder();
        defaultHandler = new DefaultHandler(responseBuilder);
        formHandler = new FormHandler(responseBuilder);
        subject = new Router(defaultHandler, formHandler);
    }

    @Test
    void routeChoosesADefaultHandlerWhenTheUrlIsRoot() {
        IHandler result = subject.route("GET", "/");

        assertTrue(result instanceof DefaultHandler);
    }

    @Test
    void routeChoosesAFormHandlerWhenTheUrlIsForm() {
        IHandler result = subject.route("GET", "/form");

        assertTrue(result instanceof FormHandler);
    }
}