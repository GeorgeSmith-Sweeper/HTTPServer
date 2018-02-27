package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ServerTest {

    private StreamMaker mockedStreamMaker;
    private Streams mockedStreams;
    private RequestParser mockedRequestParser;
    private HashMap mockedClientRequest;
    private RequestHandler mockedRequestHandler;
    private ResponseSender mockedResponseSender;
    private String mockedServerResponse;
    private String publicFolderPath;
    private int port;

    @BeforeEach
    public void setUp() {
        mockedStreamMaker = mock(StreamMaker.class);
        mockedRequestHandler = mock(RequestHandler.class);
        mockedClientRequest = mock(HashMap.class);
        mockedStreams = mock(Streams.class);
        mockedRequestParser = mock(RequestParser.class);
        mockedResponseSender = mock(ResponseSender.class);
        mockedServerResponse = "Response";
        publicFolderPath = "";
        port = 6001;
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() {
        MyServer myServer = new MyServer(
                mockedStreamMaker,
                mockedRequestParser,
                mockedRequestHandler,
                mockedResponseSender,
                publicFolderPath);
        when(mockedStreamMaker.connect()).thenReturn(mockedStreams);
        when(mockedRequestParser.parse(mockedStreams.getIn())).thenReturn(mockedClientRequest);
        when(mockedRequestHandler.handle(mockedClientRequest)).thenReturn(mockedServerResponse).thenReturn("Bye");

        myServer.start();

        verify(mockedStreamMaker, atLeastOnce()).connect();
        verify(mockedRequestParser, atLeastOnce()).parse(mockedStreams.getIn());
        verify(mockedRequestHandler, atLeastOnce()).handle(mockedClientRequest);
        verify(mockedResponseSender, atLeastOnce()).send(mockedServerResponse, mockedStreams.getOut());
    }
}
