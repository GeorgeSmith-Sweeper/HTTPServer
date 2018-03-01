package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private IHandler requestHandler;
    private ResponseSender responseSender;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    IHandler requestHandler,
                    ResponseSender responseSender,
                    String publicFolderPath) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.requestHandler = requestHandler;
        this.responseSender = responseSender;
    }

    public void start() {
        String formattedResponse = "";
        while (!formattedResponse.equals("Bye")) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            ServerResponse serverResponse = requestHandler.handle(clientRequest);
            formattedResponse = serverResponse.format();
            System.out.println("formattedResponse: " + formattedResponse);
            responseSender.send(formattedResponse, streams.getOut());
        }
    }
}
