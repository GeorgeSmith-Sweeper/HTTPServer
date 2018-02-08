package com.GeorgesServer.app;

import java.io.IOException;

public class MyServer {
    private EstablishesConnection establishesConnection;
    private RequestParser requestParser;

    public MyServer(EstablishesConnection establishesConnection, RequestParser requestParser, String publicFolderPath) {

        this.establishesConnection = establishesConnection;
        this.requestParser = requestParser;
    }

    public void start(int port) throws IOException {
        Connections connections = establishesConnection.connect(port);
        requestParser.parse(connections.getRequest());
//        Request request = parser.parse(connection.in());
//        Response response = handleRequest.handle(request);
//        sendResponse(response);
    }
}
