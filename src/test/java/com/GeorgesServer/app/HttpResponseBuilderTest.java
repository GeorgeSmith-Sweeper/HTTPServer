package com.GeorgesServer.app;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseBuilderTest {

    @Test
    void getResponseReturnsAServerResponseObject() {
        HttpResponseBuilder subject = new HttpResponseBuilder();

        assertTrue(subject.getResponse() instanceof ServerResponse);
    }

    @Test
    void buildOkStatusBuildsAValid200Status() {
        HttpResponseBuilder subject = new HttpResponseBuilder();
        subject.buildOkStatus();

        assertEquals(subject.getResponse().getStatus(), "200 OK");
    }
}