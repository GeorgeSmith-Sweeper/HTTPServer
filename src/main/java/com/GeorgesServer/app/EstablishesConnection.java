package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class EstablishesConnection {

    private SocketFactory socketFactory;

    public EstablishesConnection (SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public Connections connect(int port) {
        ServerSocket serverSocket = socketFactory.createSocket(port);
        Socket clientSocket = socketFactory.createClientSocket(serverSocket);
        InputStream inputStream = socketFactory.createInputStream(clientSocket);
        InputStreamReader inputStreamReader = socketFactory.createInputStreamReader(inputStream);
        BufferedReader bufferedReader = socketFactory.createBufferedReader(inputStreamReader);

        return new Connections(bufferedReader, clientSocket);
    }
}
