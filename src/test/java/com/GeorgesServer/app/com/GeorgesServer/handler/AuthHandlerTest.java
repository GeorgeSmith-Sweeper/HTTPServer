package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AuthHandlerTest {
    private AuthHandler subject;
    private ClientRequest mockClientRequest;
    String publicFolderPath = "../cob_spec/public/";

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new AuthHandler(publicFolderPath, mockClientRequest);
    }

    @Test
    void AuthHandlerRespondsWithAChallengeWhenUserIsUnauthorized() {
        String expectedStatus = "HTTP/1.1 401 Unauthorized";
        String expectedHeader = "Basic realm=WallyWorld";

        subject.handle();
        String header = subject.getHeaders().get("WWW-Authenticate");
        String status = subject.getStatus();

        assertEquals(expectedHeader, header);
        assertEquals(expectedStatus, status);
    }

}