package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.RequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RequestHandlerTest {
    private RequestHandler subject;
    private ClientRequest mockClientRequest;
    private HttpResponseBuilder mockResponseBuilder;
    private String OK_STATUS = "HTTP/1.1 " + StatusCodes.OK +  "\n";

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        mockResponseBuilder = mock(HttpResponseBuilder.class);
        subject = new RequestHandler(mockResponseBuilder);
    }

    @Test
    void handlerCallsTheCorrectMethodsWhenBuildingARootResponse() {

        subject.handle(mockClientRequest);

        verify(mockResponseBuilder).buildHttpVersion();
        verify(mockResponseBuilder).buildOkStatus();
        verify(mockResponseBuilder).getResponse();
    }

//    @Test
//    void handlerFormatsAResponseWithAPostRequestWithNoBody() {
//        when(mockClientRequest.getUrl()).thenReturn("/form");
//        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");
//        when(mockClientRequest.getMethod()).thenReturn("POST");
//
//        String result = subject.handle(mockClientRequest);
//
//        assertEquals(OK_STATUS, result);
//    }
//
//    @Test
//    void handlerFormatsAResponseWithAHeadRequestWithNoBody() {
//        when(mockClientRequest.getMethod()).thenReturn("HEAD");
//        when(mockClientRequest.getUrl()).thenReturn("/foobar");
//        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");
//
//        String expectedResponse = "HTTP/1.1 " + StatusCodes.NOT_FOUND + "\n";
//        String result = subject.handle(mockClientRequest);
//
//        assertEquals(expectedResponse, result);
//    }
//
//    @Test
//    void handlerFormatsAResponseWithPutRequestWithNoBody() {
//        when(mockClientRequest.getMethod()).thenReturn("PUT");
//        when(mockClientRequest.getUrl()).thenReturn("/form");
//        when(mockClientRequest.getHttpVersion()).thenReturn("HTTP/1.1");
//
//
//        String result = subject.handle(mockClientRequest);
//
//        assertEquals(OK_STATUS, result);
//    }
}