package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseBuilderTest {

    @Test
    void getResponseReturnsAServerResponseObject() {
        HttpResponseBuilder subject = new HttpResponseBuilder();

        assertTrue(subject.getResponse() instanceof ServerResponse);
    }

    @Test
    void buildOkStatusAssemblesTheCorrectCode() {
        HttpResponseBuilder subject = new HttpResponseBuilder();
        subject.buildOkStatus();

        assertEquals(subject.getResponse().getStatusCode(), "200");
        assertEquals(subject.getResponse().getStatusMsg(), "OK");
    }

    @Test
    void build206StatusAssemblesTheCorrectCode() {
        HttpResponseBuilder subject = new HttpResponseBuilder();
        subject.build206Status();

        assertEquals(subject.getResponse().getStatusCode(), "206");
        assertEquals(subject.getResponse().getStatusMsg(), "Partial Content");
    }
}