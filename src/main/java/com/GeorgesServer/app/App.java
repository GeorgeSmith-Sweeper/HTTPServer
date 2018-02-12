package com.GeorgesServer.app;

import java.io.*;

public class App {
    public static void main(String args[]) throws IOException {
        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        RequestHandler requestHandler = new RequestHandler();
        ResponseSender responseSender = new ResponseSender();
        RequestParser requestParser = new RequestParser();
        SocketFactory socketFactory = new SocketFactory();
        EstablishesConnection establishesConnection = new EstablishesConnection(socketFactory);
        MyServer server = new MyServer(establishesConnection, requestParser, requestHandler, responseSender, publicFolderPath);
        server.start(port);
    }
}
