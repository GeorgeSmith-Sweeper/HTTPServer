package com.GeorgesServer.app.request;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.request.RequestLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestLoggerTest {
    private RequestLogger subject;
    private ClientRequest mockClientRequest;
    String publicFolderPath = "../cob_spec/public/";

    @BeforeEach
    private void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new RequestLogger();
    }

    @Test
    void requestLoggerStoresEachClientRequestStartLine() {
        when(mockClientRequest.getStartLine()).thenReturn("GET / HTTP/1.1");

        subject.log(mockClientRequest);

        assertEquals("GET / HTTP/1.1", subject.getLogs().get(0));
    }
}