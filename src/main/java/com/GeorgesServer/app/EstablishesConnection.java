package com.GeorgesServer.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class EstablishesConnection {

    private ServerSocket serverSocket;

    public EstablishesConnection (ServerSocket serverSocket) {

        this.serverSocket = serverSocket;
    }

    public Connections connect(int port) {

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
