package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.handler.FourOhFourHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.OptionsHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

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
    void routerChoosesAPartialContentHandlerWhenTheUrlIsPartialContent() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", " bytes=0-4");
        when(mockedClientRequest.getHeaders()).thenReturn(headers);

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof PartialContentHandler);
    }

    @Test
    void routerChoosesPostHandlerWhenTheMethodIsPost() {
        when(mockedClientRequest.getMethod()).thenReturn("POST");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof PostHandler);
    }

    @Test
    void routerChoosesOptionsHandlerWhenTheUrlIsMethod_Options() {
        when(mockedClientRequest.getMethod()).thenReturn("OPTIONS");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof OptionsHandler);
    }

    @Test
    void routerChooses404HandlerWhenTheUrlIsFoobar() {
        when(mockedClientRequest.getUrl()).thenReturn("/foobar");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof FourOhFourHandler);
    }

}