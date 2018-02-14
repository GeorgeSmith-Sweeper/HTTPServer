package com.GeorgesServer.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketFactory implements ISocketFactory {
    @Override
    public ServerSocket createSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Socket createClientSocket(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream createInputStream(Socket clientSocket) {
        try {
            return clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OutputStream createOutPutStream(Socket clientSocket) {
        try {
            return clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OutputStreamWriter createOutputStreamWriter(OutputStream outputStream) {
        return new OutputStreamWriter(outputStream);
    }

    public InputStreamReader createInputStreamReader(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

    public BufferedReader createBufferedReader(InputStreamReader inputStreamReader) {
        return new BufferedReader(inputStreamReader);
    }

}
