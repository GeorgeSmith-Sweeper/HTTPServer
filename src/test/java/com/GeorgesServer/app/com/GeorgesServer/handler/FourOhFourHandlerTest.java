package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FourOhFourHandlerTest {
    private FourOhFourHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        subject = new FourOhFourHandler();
    }


    @Test
    void FourOhFourSendRespondsWithA404StatusAndNoBody() {
        String expectedStatus = StatusCodes.NOT_FOUND;

        subject.handle();
        String status = subject.getStatus();

        assertEquals(expectedStatus, status);
    }
}