package com.GeorgesServer.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamMaker {

    private ServerSocket serverSocket;

    public StreamMaker(ServerSocket serverSocket) {

        this.serverSocket = serverSocket;
    }

    public Connections connect() {

        try {
            Socket clientSocket = this.serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            InputStreamReader inReader = new InputStreamReader(inputStream);
            OutputStreamWriter outWriter = new OutputStreamWriter(outputStream);
            BufferedReader buffReader = new BufferedReader(inReader);
            return new Connections(buffReader, outWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
