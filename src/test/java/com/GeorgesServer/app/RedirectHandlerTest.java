package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RedirectHandlerTest {
    private RedirectHandler subject;
    private ClientRequest mockClientRequest;
    String publicFolderPath = "../cob_spec/public/";

    @BeforeEach
    private void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new RedirectHandler(publicFolderPath, mockClientRequest);
    }

    @Test
    void redirectSetsA302Status() {
        String expectedStatus = "HTTP/1.1 302 Found";
        subject.handle();

        String result = subject.getStatus();

        assertEquals(expectedStatus, result);
    }


}