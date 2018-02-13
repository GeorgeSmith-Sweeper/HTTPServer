package com.GeorgesServer.app;

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

    public void start(int port) {
        Connections connections = establishesConnection.connect(port);
        ClientRequest clientRequest = requestParser.parse(connections.getOut());
        String serverResponse = requestHandler.handle(clientRequest);
        responseSender.send(serverResponse, connections.getOut());
    }
}
