package com.GeorgesServer.app;


public class App {
    public static void main(String args[]) {

        String publicFolderPath = args[3];
        int port = Integer.parseInt(args[1]);

        RequestHandler requestHandler = new RequestHandler();
        ResponseSender responseSender = new ResponseSender();
        RequestReader requestReader = new RequestReader();
        RequestParser requestParser = new RequestParser(requestReader);
        SocketFactory socketFactory = new SocketFactory();

        EstablishesConnection establishesConnection = new EstablishesConnection(socketFactory);
        MyServer server = new MyServer(establishesConnection, requestParser, requestHandler, responseSender, publicFolderPath);
        server.start(port);
    }
}
