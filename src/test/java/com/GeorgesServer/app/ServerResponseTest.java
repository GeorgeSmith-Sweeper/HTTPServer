package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerResponseTest {

    @Test
    void formatBuildsAResponseWithNoBody() {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus("200", "OK");
        serverResponse.setHttpVersion("HTTP/1.1");
        String result = serverResponse.format();

        assertEquals("HTTP/1.1 200 OK\n", result);

    }

    @Test
    void setStatusCorrectlyFormatsAStatusCode() {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setStatus("200", "OK");

        assertEquals("200 OK", serverResponse.getStatus());
    }

    @Test
    void setHttpVersionAddsTheHttpVersion() {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setHttpVersion("HTTP/1.1");

        assertEquals("HTTP/1.1", serverResponse.getHttpVersion());
    }
}