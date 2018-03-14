package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FormHandlerTest {
    private FormHandler subject;
    private ClientRequest mockClientRequest;
    private String expectedOKResponse = "HTTP/1.1 200 OK\n";

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new FormHandler();
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAGETRequest() {
        when(mockClientRequest.getMethod()).thenReturn("GET");

        subject.handle(mockClientRequest);
        String result = subject.format();

        assertEquals(expectedOKResponse, result);
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAPOSTRequest() {
        when(mockClientRequest.getMethod()).thenReturn("POST");

        subject.handle(mockClientRequest);
        String result = subject.format();

        assertEquals(expectedOKResponse, result);
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAPUTRequest() {
        when(mockClientRequest.getMethod()).thenReturn("PUT");

        subject.handle(mockClientRequest);
        String result = subject.format();

        assertEquals(expectedOKResponse, result);
    }
}