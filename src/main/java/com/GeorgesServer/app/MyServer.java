package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private ResponseSender responseSender;
    private RouterConfig routerConfig;
    private String publicFolderPath;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    ResponseSender responseSender,
                    RouterConfig routerConfig,
                    String publicFolderPath) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.responseSender = responseSender;
        this.routerConfig = routerConfig;
        this.publicFolderPath = publicFolderPath;
    }

    public void start() {
        Router router = routerConfig.getRouter();
        byte[] formattedResponse;
        while (true) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            IHandler handler = router.route(clientRequest);
            ServerResponse serverResponse = handler.handle(clientRequest);
            formattedResponse = serverResponse.format();
            responseSender.send(formattedResponse, streams.getOut());
        }
    }
}
