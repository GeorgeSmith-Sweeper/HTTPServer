package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OptionsHandlerTest {
    private OptionsHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        String publicFolderPath = "../cob_spec/public/";
        mockClientRequest = mock(ClientRequest.class);
        subject = new OptionsHandler(mockClientRequest);
    }

    @Test
    void optionsHandlerReturnsWhichMethodsAreAllowedOnMethod_OptionsURL() {
        String expectedStatus = "HTTP/1.1 200 OK";
        String expectedHeaders = "GET,HEAD,POST,OPTIONS,PUT";

        when(mockClientRequest.getUrl()).thenReturn("/method_options");

        subject.handle();
        String status = subject.getStatus();
        String headers = subject.getHeaders().get("Allow");

        assertEquals(expectedStatus, status);
        assertEquals(expectedHeaders, headers);
    }

    @Test
    void optionsHandlerReturnsWhichMethodsAreAllowedOnMethod_Options2URL() {
        String expectedStatus = "HTTP/1.1 200 OK";
        String expectedHeaders = "GET,OPTIONS";

        when(mockClientRequest.getUrl()).thenReturn("/method_options2");

        subject.handle();
        String status = subject.getStatus();
        String headers = subject.getHeaders().get("Allow");

        assertEquals(expectedStatus, status);
        assertEquals(expectedHeaders, headers);
    }
}