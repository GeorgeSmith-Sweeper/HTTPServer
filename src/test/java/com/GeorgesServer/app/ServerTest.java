package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import com.GeorgesServer.app.com.GeorgesServer.IO.ResponseSender;
import com.GeorgesServer.app.com.GeorgesServer.IO.StreamMaker;
import com.GeorgesServer.app.com.GeorgesServer.IO.Streams;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.request.RequestParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServerTest {

    private StreamMaker mockedStreamMaker;
    private Streams mockedStreams;
    private RequestParser mockedRequestParser;
    private ClientRequest mockedClientRequest;
    private IHandler mockedHandler;
    private ResponseSender mockedResponseSender;
    private String mockedFormattedResponse;
    private Router mockedRouter;
    private String publicFolderPath = "";

    @BeforeEach
    private void setUp() {
        mockedStreamMaker = mock(StreamMaker.class);
        mockedHandler = mock(IHandler.class);
        mockedClientRequest = mock(ClientRequest.class);
        mockedStreams = mock(Streams.class);
        mockedRequestParser = mock(RequestParser.class);
        mockedResponseSender = mock(ResponseSender.class);
        mockedRouter = mock(Router.class);
        mockedFormattedResponse = "";
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() {
        MyServer myServer = new MyServer(
                mockedStreamMaker,
                mockedRequestParser,
                mockedResponseSender,
                mockedRouter,
                publicFolderPath);

        when(mockedStreamMaker.connect()).thenReturn(mockedStreams);
        when(mockedRequestParser.parse(mockedStreams.getIn())).thenReturn(mockedClientRequest);
        when(mockedRouter.route(publicFolderPath, mockedClientRequest)).thenReturn(mockedHandler);
        when(mockedHandler.format()).thenReturn(mockedFormattedResponse).thenReturn("Bye");

        myServer.start();

        verify(mockedStreamMaker, atLeastOnce()).connect();
        verify(mockedRequestParser, atLeastOnce()).parse(mockedStreams.getIn());
        verify(mockedRouter, atLeastOnce()).route(publicFolderPath, mockedClientRequest);
        verify(mockedHandler, atLeastOnce()).handle();
        verify(mockedHandler, atLeastOnce()).format();
        verify(mockedResponseSender, atLeastOnce()).send(mockedFormattedResponse, mockedStreams.getOut());
    }
}
