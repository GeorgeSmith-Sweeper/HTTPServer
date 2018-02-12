package com.GeorgesServer.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EstablishesConnection {

    private SocketFactory socketFactory;

    public EstablishesConnection (SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public Connections connect(int port) {
        try (ServerSocket serverSocket = socketFactory.createSocket(port);
             Socket clientSocket = socketFactory.createClientSocket(serverSocket)) {
            return new Connections(serverSocket, clientSocket);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
