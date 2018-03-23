package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        String expectedStatus = StatusCodes.FOUND;
        subject.handle();

        String result = subject.getStatus();

        assertEquals(expectedStatus, result);
    }

    @Test
    void redirectSetsALocationHeaderToTheRoot() {
        String expectedLocation = "/";
        subject.handle();

        String result = subject.getHeaders().get("Location");

        assertEquals(expectedLocation, result);
    }


}