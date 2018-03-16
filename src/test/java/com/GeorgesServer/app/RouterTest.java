package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterTest {
    private Router subject;
    private ClientRequest mockedClientRequest;
    private String publicFolderPath = "";

    @BeforeEach
    private void setUp() {
        mockedClientRequest = mock(ClientRequest.class);
        subject = new Router();
    }

    @Test
    void routeChoosesAPartialContentHandlerWhenTheUrlIsPartialContent() {
        when(mockedClientRequest.getUrl()).thenReturn("/partial_content.txt");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof PartialContentHandler);
    }

}