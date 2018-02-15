package com.GeorgesServer.app;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketMaker {

    private ServerSocket serverSocket;

    public void createServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }
}
