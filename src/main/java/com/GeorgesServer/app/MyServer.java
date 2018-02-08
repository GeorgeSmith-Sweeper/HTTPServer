package com.GeorgesServer.app;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServer {
    private EstablishesConnection establishesConnection;

    public MyServer(EstablishesConnection establishesConnection, String publicFolderPath) {

        this.establishesConnection = establishesConnection;
    }

    public void start(int port) throws IOException {
        Connections connections = establishesConnection.connect(port);

//        Request request = parser.parse(connection.in());
//        Response response = handleRequest.handle(request);
//        sendResponse(response);
    }
}
