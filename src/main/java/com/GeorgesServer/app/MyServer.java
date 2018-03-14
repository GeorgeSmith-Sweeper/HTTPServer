package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class MyServer {
    private StreamMaker streamMaker;
    private RequestParser requestParser;
    private ResponseSender responseSender;
    private RouterConfig routerConfig;

    public MyServer(StreamMaker streamMaker,
                    RequestParser requestParser,
                    ResponseSender responseSender,
                    RouterConfig routerConfig) {

        this.streamMaker = streamMaker;
        this.requestParser = requestParser;
        this.responseSender = responseSender;
        this.routerConfig = routerConfig;
    }

    public void start() {
        Router router = routerConfig.getRouter();
        String formattedResponse = "";
        while (!formattedResponse.equals("Bye")) {
            Streams streams = streamMaker.connect();
            ClientRequest clientRequest = requestParser.parse(streams.getIn());
            IHandler handler = router.route(clientRequest);
            handler.handle(clientRequest);
            formattedResponse = handler.format();
            responseSender.send(formattedResponse, streams.getOut());
        }
    }


}
