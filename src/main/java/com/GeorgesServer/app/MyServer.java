package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private ResponseSender responseSender;
    private Router router;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    ResponseSender responseSender,
                    Router router,
                    String publicFolderPath) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.responseSender = responseSender;
        this.router = router;
    }

    public void start() {
        String formattedResponse = "";
        while (!formattedResponse.equals("Bye")) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            IHandler handler = router.route(clientRequest.getMethod(), clientRequest.getUrl());
            ServerResponse serverResponse = handler.handle(clientRequest);
            formattedResponse = serverResponse.format();
            responseSender.send(formattedResponse, streams.getOut());
        }
    }
}
