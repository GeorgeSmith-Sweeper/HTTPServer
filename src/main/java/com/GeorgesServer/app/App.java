package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        DefaultHandler defaultHandler = new DefaultHandler(responseBuilder);
        ResponseSender responseSender = new ResponseSender();
        RequestReader requestReader = new RequestReader();
        RequestParser requestParser = new RequestParser(requestReader);
        Router router = new Router();

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
                defaultHandler,
                responseSender,
                router,
                publicFolderPath);

        server.start();
    }
}
