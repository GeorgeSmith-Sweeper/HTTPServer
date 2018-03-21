package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
        String url = "/";

        subject.setUrl(url);

        assertEquals(url, subject.getUrl());
    }

    @Test
    void setHeadersSetsTheFieldHeaders() {
        HashMap<String, String> headers = new HashMap<>();


        subject.setHeaders(headers);

        assertEquals(headers, subject.getHeaders());
    }

    @Test
    void setBodySetsTheBodyField() {
        String body = "\"My\"=\"Data\"";

        subject.setBody(body);

        assertEquals(body, subject.getBody());
    }

    @Test
    void setStartLineSetsTheStartLineField() {
        String startLine = "GET / HTTP/1.1";

        subject.setStartLine(startLine);

        assertEquals(startLine, subject.getStartLine());
    }
}