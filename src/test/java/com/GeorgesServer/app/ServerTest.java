package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ServerTest {

    private EstablishesConnection mockedEstablishesConnection;
    private Connections mockedConnections;
    private RequestParser mockedRequestParser;
    private HashMap mockedClientRequest;
    private RequestHandler mockedRequestHandler;
    private ResponseSender mockedResponseSender;
    private String mockedServerResponse;
    private String publicFolderPath;
    private int port;

    @BeforeEach
    public void setUp() {
        mockedEstablishesConnection = mock(EstablishesConnection.class);
        mockedRequestHandler = mock(RequestHandler.class);
        mockedClientRequest = mock(HashMap.class);
        mockedConnections = mock(Connections.class);
        mockedRequestParser = mock(RequestParser.class);
        mockedResponseSender = mock(ResponseSender.class);
        mockedServerResponse = "";
        publicFolderPath = "";
        port = 5000;
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() {
        MyServer myServer = new MyServer(
                mockedEstablishesConnection,
                mockedRequestParser,
                mockedRequestHandler,
                mockedResponseSender,
                publicFolderPath);
        when(mockedEstablishesConnection.connect(port)).thenReturn(mockedConnections);
        when(mockedRequestParser.parse(mockedConnections.getIn())).thenReturn(mockedClientRequest);
        when(mockedRequestHandler.handle(mockedClientRequest)).thenReturn(mockedServerResponse);

        myServer.start(port);

        verify(mockedEstablishesConnection).connect(port);
        verify(mockedRequestParser).parse(mockedConnections.getIn());
        verify(mockedRequestHandler).handle(mockedClientRequest);
        verify(mockedResponseSender).send(mockedServerResponse, mockedConnections.getOut());
    }
}
