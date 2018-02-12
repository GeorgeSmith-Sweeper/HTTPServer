package com.GeorgesServer.app;

import java.net.ServerSocket;
import java.net.Socket;

public interface ISocketFactory {
    ServerSocket createSocket(int port);
    Socket createClientSocket(ServerSocket serverSocket);
}
