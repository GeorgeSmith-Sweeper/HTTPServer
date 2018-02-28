package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.handler.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        RequestHandler requestHandler = new RequestHandler(responseBuilder);
        ResponseSender responseSender = new ResponseSender();
        RequestReader requestReader = new RequestReader();
        RequestParser requestParser = new RequestParser(requestReader);

        MyServer server;
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        StreamMaker streamMaker = new StreamMaker(serverSocket);
        server = new MyServer(
                streamMaker,
                requestParser,
                requestHandler,
                responseSender,
                publicFolderPath);

        server.start();
    }
}
