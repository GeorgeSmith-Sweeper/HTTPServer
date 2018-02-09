package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ServerTest {

    private EstablishesConnection mockedEstablishesConnection;
    private Connections mockedConnections;
    private RequestParser mockedRequestParser;
    private ClientRequest mockedClientRequest;
    private RequestHandler mockedRequestHandler;
    private int port;
    private String publicFolderPath;

    @BeforeEach
    public void setUp() {
        mockedEstablishesConnection = mock(EstablishesConnection.class);
        mockedRequestHandler = mock(RequestHandler.class);
        mockedClientRequest = mock(ClientRequest.class);
        mockedConnections = mock(Connections.class);
        mockedRequestParser = mock(RequestParser.class);
        port = 5000;
        publicFolderPath = "";
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() throws IOException {
        MyServer myServer = new MyServer(
                mockedEstablishesConnection,
                mockedRequestParser,
                mockedRequestHandler,
                publicFolderPath);
        when(mockedEstablishesConnection.connect(port)).thenReturn(mockedConnections);
        when(mockedRequestParser.parse(mockedConnections.getRequest())).thenReturn(mockedClientRequest);

        myServer.start(port);

        verify(mockedEstablishesConnection).connect(port);
        verify(mockedRequestParser).parse(mockedConnections.getRequest());
        verify(mockedRequestHandler).handle(mockedClientRequest);
    }

}
