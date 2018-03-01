package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RouterTest {
    private Router subject;
    private IHandler defaultHandler;
    private HttpResponseBuilder responseBuilder;

    @BeforeEach
    private void setUp() {
        responseBuilder = new HttpResponseBuilder();
        defaultHandler = new DefaultHandler(responseBuilder);
        subject = new Router(defaultHandler);
    }

    @Test
    void routeChoosesADefaultHandlerWhenTheUrlIsRoot() {
        IHandler result = subject.route("GET", "/");

        assertTrue(result instanceof DefaultHandler);
    }
}