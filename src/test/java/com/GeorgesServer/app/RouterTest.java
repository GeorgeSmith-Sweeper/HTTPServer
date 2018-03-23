package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.handler.*;
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
    private String publicFolderPath = "../cob_spec/public/";

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
    void routerChoosesOptionsHandlerWhenTheMethodIsOptions() {
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

    @Test
    void routerChoosesFilesHandlerWhenAFileExistsInTheDirectory() {
        when(mockedClientRequest.getUrl()).thenReturn("/file1");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof FilesHandler);
    }

    @Test
    void routerChoosesAuthHandlerWhenAProtectedUrlIsRequested() {
        when(mockedClientRequest.getUrl()).thenReturn("/logs");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof AuthHandler);
    }

    @Test
    void routerChoosesDirectoryHandlerWhenADirectoryIsRequested() {
        when(mockedClientRequest.getUrl()).thenReturn("/");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof DirectoryHandler);
    }

    @Test
    void routerChoosesParameterHandlerWhenAUrlContainsAQuestionMark() {
        when(mockedClientRequest.getUrl()).thenReturn("/parameters?foo=bar");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof ParameterHandler);
    }

    @Test
    void routerChoosesTepotHandlerWhenAUrlContainsAQuestionMark() {
        when(mockedClientRequest.getUrl()).thenReturn("/parameters?foo=bar");
        when(mockedClientRequest.getMethod()).thenReturn("GET");

        IHandler result = subject.route(publicFolderPath, mockedClientRequest);

        assertTrue(result instanceof ParameterHandler);
    }

}