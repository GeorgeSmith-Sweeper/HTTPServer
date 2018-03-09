package com.GeorgesServer.app;


import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        String folder = publicFolderPath;
        String fileWeWant = "/partial_content.txt";
        Path file = Paths.get(folder + fileWeWant);

        StringBuilder sb = new StringBuilder();
        String fileContents = "";
        byte[] fileArray;
        byte[] byteArray = new byte[4];
        try {
            fileArray = Files.readAllBytes(file);
            System.out.println("byteArrayLength: " + fileArray.length);

            for (int start = 0; start < 4; start++) {
                byteArray[start] = fileArray[start];
                System.out.println(byteArray[start]);
            }

            System.out.println(byteArray.length);
            for (byte letter : fileArray) {
                char character = (char) letter;
                sb.append(character);
            }
            fileContents = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rangeStartAndEnd = fileContents.substring(0, 4);
        System.out.println("rangeStartAndEnd: " + rangeStartAndEnd);

        String noRangeStart = fileContents.substring(fileContents.length()-6);
        System.out.println("noRangeStart: " + noRangeStart);

        String noRangeEnd = fileContents.substring(4);
        System.out.println("noRangeEnd: " + noRangeEnd);


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
