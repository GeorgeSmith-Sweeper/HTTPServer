package com.GeorgesServer.app;


import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        RequestHandler requestHandler = new RequestHandler();
        ResponseSender responseSender = new ResponseSender();
        RequestReader requestReader = new RequestReader();
        RequestParser requestParser = new RequestParser(requestReader);

        EstablishesConnection establishesConnection;
        MyServer server;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            establishesConnection = new EstablishesConnection(serverSocket);
            server = new MyServer(establishesConnection, requestParser, requestHandler, responseSender, publicFolderPath);
            server.start(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
