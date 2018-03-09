package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        HandlerCreator handlerCreator = new HandlerCreator();
        RouterConfig routerConfig = new RouterConfig(handlerCreator.getHandlers());

        ResponseSender responseSender = new ResponseSender();
        RequestReader requestReader = new RequestReader();
        RequestParser requestParser = new RequestParser(requestReader);

        MyServer server;
        ServerSocket serverSocket;

        String path = publicFolderPath + "/partial_content.txt";
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        byte[] fileArray;
        try {
            fileArray = Files.readAllBytes(file);
            for (byte letter : fileArray) {
                System.out.println(letter);

                char character = (char) letter;
                sb.append(character);
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }



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
                routerConfig,
                publicFolderPath);

        server.start();
    }
}
