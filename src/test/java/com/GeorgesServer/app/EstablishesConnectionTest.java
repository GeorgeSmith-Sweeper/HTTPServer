package com.GeorgesServer.app;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EstablishesConnectionTest {
    private EstablishesConnection subject;
    private int validPortNumber = 5000;
    private String invalidPortNumber = "invalid";
    private ServerSocket mockedServerAccept;
    private Socket mockedSocket;

    @BeforeEach
    void setUp () {
        subject = new EstablishesConnection();
        mockedServerAccept = mock(ServerSocket.class);
        mockedSocket = mock(Socket.class);
    }

    @Test
    void establishesConnectionReturnsAConnectionsObject() throws IOException { /* TODO */ }
}
