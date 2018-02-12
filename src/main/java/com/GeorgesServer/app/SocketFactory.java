package com.GeorgesServer.app;

import java.io.IOException;
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
}
