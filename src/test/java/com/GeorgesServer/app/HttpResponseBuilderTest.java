package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseBuilderTest {

    HttpResponseBuilder subject;

    @BeforeEach
    void setUp() {
        subject = new HttpResponseBuilder();
    }

    @Test
    void getResponseReturnsAServerResponseObject() {

        assertTrue(subject.getResponse() instanceof ServerResponse);
    }

    @Test
    void buildOkStatusAssemblesTheCorrectCode() {
        subject.buildOkStatus();

        assertEquals(subject.getResponse().getStatusCode(), "200");
        assertEquals(subject.getResponse().getStatusMsg(), "OK");
    }

    @Test
    void build206StatusAssemblesTheCorrectCode() {
        subject.build206Status();

        assertEquals(subject.getResponse().getStatusCode(), "206");
        assertEquals(subject.getResponse().getStatusMsg(), "Partial Content");
    }
}