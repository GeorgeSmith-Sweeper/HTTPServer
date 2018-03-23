package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.IO.ResponseSender;
import com.GeorgesServer.app.com.GeorgesServer.IO.StreamMaker;
import com.GeorgesServer.app.com.GeorgesServer.IO.Streams;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.request.RequestParser;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private ResponseSender responseSender;
    private Router router;
    private String publicFolderPath;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    ResponseSender responseSender,
                    Router router,
                    String publicFolderPath) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.responseSender = responseSender;
        this.router = router;
        this.publicFolderPath = publicFolderPath;
    }

    public void start() {
        String formattedResponse = "";
        while (!formattedResponse.equals("Bye")) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            IHandler handler = router.route(publicFolderPath, clientRequest);
            handler.handle();
            formattedResponse = handler.format();
            responseSender.send(formattedResponse, streams.getOut());
        }
    }


}
