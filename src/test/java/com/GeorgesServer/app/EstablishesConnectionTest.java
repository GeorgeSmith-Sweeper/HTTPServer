package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EstablishesConnectionTest {
    private EstablishesConnection subject;
    private SocketFactory mockedSocketFactory;
    private ServerSocket mockedServerSocket;
    private Socket mockedClientSocket;

    @BeforeEach
    void setUp () {
        mockedSocketFactory = mock(SocketFactory.class);
        mockedServerSocket = mock(ServerSocket.class);
        mockedClientSocket = mock(Socket.class);
        subject = new EstablishesConnection(mockedSocketFactory);
    }

    @Test
    void establishesConnectionCreatesAnInputAndOutputConnection() {
        int validPortNumber = 5000;
        when(mockedSocketFactory.createSocket(validPortNumber)).thenReturn(mockedServerSocket);
        when(mockedSocketFactory.createClientSocket(mockedServerSocket)).thenReturn(mockedClientSocket);

        Connections result = subject.connect(validPortNumber);
        assertTrue(result.getIn() != null);
        assertTrue(result.getOut() != null);
    }
}
