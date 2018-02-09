package com.GeorgesServer.app;

import java.io.IOException;

public class MyServer {
    private EstablishesConnection establishesConnection;
    private RequestParser requestParser;
    private RequestHandler requestHandler;

    public MyServer(EstablishesConnection establishesConnection, RequestParser requestParser, RequestHandler requestHandler, String publicFolderPath) {

        this.establishesConnection = establishesConnection;
        this.requestParser = requestParser;
        this.requestHandler = requestHandler;
    }

    public void start(int port) throws IOException {
        Connections connections = establishesConnection.connect(port);
        ClientRequest clientRequest = requestParser.parse(connections.getRequest());
        requestHandler.handle(clientRequest);
//        Request request = parser.parse(connection.in());
//        Response response = handleRequest.handle(request);
//        sendResponse(response);
    }
}
