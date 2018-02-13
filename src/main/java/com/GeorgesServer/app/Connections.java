package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Connections {

    private BufferedReader bufferedReader;
    private Socket clientConnection;

    public Connections (BufferedReader bufferedReader, Socket clientConnection) {
        this.bufferedReader = bufferedReader;
        this.clientConnection = clientConnection;
    }

    public BufferedReader getIn() {
        return this.bufferedReader;
    }

    public Socket getOut() {
        return this.clientConnection;
    }
}
