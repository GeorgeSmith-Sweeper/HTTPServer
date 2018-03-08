package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FormHandlerTest {
    private FormHandler subject;
    private ClientRequest mockClientRequest;
    private String expectedCode = "200";
    private String expectedMsg = "OK";
    private String expectedVersion = "HTTP/1.1";

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new FormHandler();
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAGETRequest() {
        when(mockClientRequest.getMethod()).thenReturn("GET");

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAPOSTRequest() {
        when(mockClientRequest.getMethod()).thenReturn("POST");

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAPUTRequest() {
        when(mockClientRequest.getMethod()).thenReturn("PUT");

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
    }
}