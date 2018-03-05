package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        RouterConfig config = new RouterConfig(responseBuilder);
        Router router = config.getRouter();

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
                responseSender,
                router,
                publicFolderPath);

        server.start();
    }
}
