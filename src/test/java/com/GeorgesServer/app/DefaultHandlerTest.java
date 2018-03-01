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
}