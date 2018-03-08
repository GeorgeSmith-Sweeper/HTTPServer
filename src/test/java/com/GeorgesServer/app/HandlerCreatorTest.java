package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HandlerCreatorTest {
    private HandlerCreator subject;

    @BeforeEach
    void setUp() {
        subject = new HandlerCreator();
    }

    @Test
    void makeHandlersCreatesHandlersForASpecificKey() {
        subject.makeHandlers();

        assertTrue(subject.getHandlers().get("defaultHandler") instanceof DefaultHandler);
        assertTrue(subject.getHandlers().get("formHandler") instanceof FormHandler);
        assertTrue(subject.getHandlers().get("partialContentHandler") instanceof PartialContentHandler);
    }
}