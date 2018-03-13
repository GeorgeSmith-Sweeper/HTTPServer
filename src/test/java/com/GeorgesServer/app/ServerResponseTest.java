package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerResponseTest {
    private ClientRequest mockedClientRequest;
    private ServerResponse subject;

    @BeforeEach
    void setUp() {
        mockedClientRequest = mock(ClientRequest.class);
        subject = new ServerResponse();
    }

    @Test
    void formatBuildsAResponseWithNoBody() {
        subject.setStatusCode("200");
        subject.setStatusMsg("OK");
        subject.setHttpVersion("HTTP/1.1");
        when(mockedClientRequest.getUrl()).thenReturn("/");
        String result = subject.format(mockedClientRequest);

        assertEquals("HTTP/1.1 200 OK\n", result);
    }

    @Test
    void setStatusCodeCorrectlyStoresAStatusCode() {
        subject.setStatusCode("200");

        assertEquals("200", subject.getStatusCode());
    }

    @Test
    void setStatusMsgCorrectlyStoresAStatusMsg() {
        subject.setStatusMsg("OK");

        assertEquals("OK", subject.getStatusMsg());
    }

    @Test
    void setHttpVersionAddsTheHttpVersion() {
        subject.setHttpVersion("HTTP/1.1");

        assertEquals("HTTP/1.1", subject.getHttpVersion());
    }
}