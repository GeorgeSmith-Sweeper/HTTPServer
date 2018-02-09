package com.GeorgesServer.app;

import java.net.ServerSocket;
import java.net.Socket;

public class Connections {

    private ServerSocket serverConnection;
    private Socket clientConnection;

    public Connections (ServerSocket serverConnection, Socket clientConnection) {

        this.serverConnection = serverConnection;
        this.clientConnection = clientConnection;
    }

    public ServerSocket getRequest() {
        return this.serverConnection;
    }
}
