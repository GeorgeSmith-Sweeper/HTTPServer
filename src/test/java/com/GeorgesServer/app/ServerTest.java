package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ServerTest {

    private EstablishesConnection mockedEstablishesConnection;
    private Connections mockedConnections;
    private int port;
    private String publicFolderPath;
    private RequestParser mockedRequestParser;

    @BeforeEach
    public void setUp() {
        mockedEstablishesConnection = mock(EstablishesConnection.class);
        mockedConnections = mock(Connections.class);
        port = 5000;
        publicFolderPath = "";
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() throws IOException {
        MyServer myServer = new MyServer(mockedEstablishesConnection, publicFolderPath);
        myServer.start(port);

        verify(mockedEstablishesConnection).connect(port);
        verify(mockedRequestParser).parse(mockedConnections.getRequest());
    }
}
