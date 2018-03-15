package com.GeorgesServer.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamMaker {

    private ServerSocket serverSocket;

    public StreamMaker(ServerSocket serverSocket) {

        this.serverSocket = serverSocket;
    }

    public Streams connect() {

        try {
            Socket clientSocket = this.serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            return new Streams(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
