package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.IO.RequestReader;
import com.GeorgesServer.app.com.GeorgesServer.IO.ResponseSender;
import com.GeorgesServer.app.com.GeorgesServer.IO.StreamMaker;
import com.GeorgesServer.app.com.GeorgesServer.request.RequestParser;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        Router router = new Router();
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
