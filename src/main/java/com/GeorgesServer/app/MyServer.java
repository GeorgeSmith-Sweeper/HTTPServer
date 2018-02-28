package com.GeorgesServer.app;

import java.util.HashMap;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private RequestHandler requestHandler;
    private ResponseSender responseSender;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    RequestHandler requestHandler,
                    ResponseSender responseSender,
                    String publicFolderPath) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.requestHandler = requestHandler;
        this.responseSender = responseSender;
    }

    public void start() {
        String serverResponse = "";
        while (!serverResponse.equals("Bye")) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            serverResponse = requestHandler.handle(clientRequest);
            responseSender.send(serverResponse, streams.getOut());
        }
    }
}
