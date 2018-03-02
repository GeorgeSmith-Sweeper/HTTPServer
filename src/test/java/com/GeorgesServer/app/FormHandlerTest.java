package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FormHandlerTest {
    private FormHandler formHandler;
    private ClientRequest mockClientRequest;
    private HttpResponseBuilder mockResponseBuilder;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        mockResponseBuilder = mock(HttpResponseBuilder.class);
        formHandler = new FormHandler(mockResponseBuilder);
    }

    @Test
    void formHandlerCallsTheCorrectMethodsWhenGivenAGETRequest() {

        when(mockClientRequest.getMethod()).thenReturn("GET");

        formHandler.handle(mockClientRequest);

        verify(mockResponseBuilder).buildHttpVersion();
        verify(mockResponseBuilder).buildOkStatus();
        verify(mockResponseBuilder).getResponse();
    }

//    @Test
//    void formHandlerCallsTheCorrectMethodsWhenGivenAPOSTRequest() {
//
//        when(mockClientRequest.getMethod()).thenReturn("POST");
//
//        formHandler.handle(mockClientRequest);
//
//        verify(mockResponseBuilder).buildHttpVersion();
//        verify(mockResponseBuilder).buildOkStatus();
//        verify(mockResponseBuilder).getResponse();
//    }
}