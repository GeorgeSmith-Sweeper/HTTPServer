package com.GeorgesServer.app;

import java.io.*;

public class App {
    public static void main(String args[]) throws IOException{
        String publicFolderPath = args[3];
        Server server = new Server(5000, publicFolderPath);
        server.start();
    }
}
