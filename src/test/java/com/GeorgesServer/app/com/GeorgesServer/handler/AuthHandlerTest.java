package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.RequestLogger;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthHandlerTest {
    private AuthHandler subject;
    private ClientRequest mockClientRequest;
    private RequestLogger mockRequestLogger;
    private ArrayList<String> logs;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        mockRequestLogger = mock(RequestLogger.class);
        logs = new ArrayList<>();
        subject = new AuthHandler(mockClientRequest, mockRequestLogger);
    }

    @Test
    void AuthHandlerSetsA401StatusWhenARequestIsUnauthorized() {
        String expectedStatus = "HTTP/1.1 401 Unauthorized";

        subject.handle();
        String status = subject.getStatus();

        assertEquals(expectedStatus, status);
    }


    @Test
    void AuthHandlerSetsA200StatusCorrectCredentialsAreProvided() {
        String expectedStatus = "HTTP/1.1 200 OK";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        when(mockClientRequest.getHeaders()).thenReturn(headers);

        subject.handle();
        String status = subject.getStatus();

        assertEquals(expectedStatus, status);
    }

    @Test
    void AuthHandlerSetsAChallengeHeaderWhenARequestIsUnauthorized() {
        String expectedValue = "Basic realm=WallyWorld";

        subject.handle();
        String result = subject.getHeaders().get("WWW-Authenticate");

        assertEquals(expectedValue, result);
    }

    @Test
    void AuthHandlerSetsABlankBodyWhenRequestIsUnauthorized() {
        String expectedBody = "";

        subject.handle();
        String result = subject.getBody();

        assertEquals(expectedBody, result);
    }

    @Test
    void AuthHandlerSetsBodyEqualToLogsWhenRequestIsAuthorized() {
        String expectedBody = "nullGET / HTTP/1.1\n";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        logs.add("GET / HTTP/1.1");
        when(mockRequestLogger.getLogs()).thenReturn(logs);
        when(mockClientRequest.getHeaders()).thenReturn(headers);

        subject.handle();
        String result = subject.getBody();

        assertEquals(expectedBody, result);
    }

}