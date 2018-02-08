package com.GeorgesServer.app;

import java.io.*;

public class App {
    public static void main(String args[]) throws IOException {
        String publicFolderPath = args[4];
        int port = Integer.parseInt(args[1]);

        // The following should go into a config
        RequestParser requestParser = new RequestParser();
        EstablishesConnection establishesConnection = new EstablishesConnection();
        MyServer server = new MyServer(establishesConnection, requestParser, publicFolderPath);
        server.start(port);
    }
}
