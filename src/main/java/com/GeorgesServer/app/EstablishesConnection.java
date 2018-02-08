package com.GeorgesServer.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EstablishesConnection {

    public Connections connect(int port) throws IOException {
        try (ServerSocket serverConnection = new ServerSocket(port);
             Socket clientConnection = serverConnection.accept()) {
            return new Connections(serverConnection, clientConnection);
        }
    }
}
