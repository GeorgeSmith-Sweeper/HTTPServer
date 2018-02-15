package com.GeorgesServer.app;

import java.util.HashMap;

public class MyServer {
    private EstablishesConnection establishesConnection;
    private RequestParser requestParser;
    private RequestHandler requestHandler;
    private ResponseSender responseSender;

    public MyServer(EstablishesConnection establishesConnection, RequestParser requestParser, RequestHandler requestHandler, ResponseSender responseSender, String publicFolderPath) {

        this.establishesConnection = establishesConnection;
        this.requestParser = requestParser;
        this.requestHandler = requestHandler;
        this.responseSender = responseSender;
    }

    public void start() {
        Connections connections = establishesConnection.connect();
        HashMap<String, String> clientRequest = requestParser.parse(connections.getIn());
        String serverResponse = requestHandler.handle(clientRequest);
        responseSender.send(serverResponse, connections.getOut());
    }
}
