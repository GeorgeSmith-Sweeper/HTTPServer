package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRequestTest {
    private ClientRequest subject;

    @BeforeEach
    public void setUp() {
        subject = new ClientRequest();
    }

    @Test
    void setHttpVersionSetsTheFieldHttpVersion() {
        String httpVersion = "HTTP/1.1";

        subject.setHttpVersion(httpVersion);

        assertEquals(httpVersion, subject.getHttpVersion());
    }

    @Test
    void setMethodSetsTheFieldMethod() {
        String method = "GET";

        subject.setMethod(method);

        assertEquals(method, subject.getMethod());
    }

    @Test
    void setUrlSetsTheFieldUrl() {
        String Url = "/";

        subject.setUrl(Url);

        assertEquals(Url, subject.getUrl());
    }

}