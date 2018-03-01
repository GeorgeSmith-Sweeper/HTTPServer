package com.GeorgesServer.app.com.GeorgesServer.handler;


import com.GeorgesServer.app.ClientRequest;
import com.GeorgesServer.app.HttpResponseBuilder;
import com.GeorgesServer.app.ServerResponse;

public class RequestHandler implements IHandler {

    private HttpResponseBuilder responseBuilder;

    public RequestHandler(HttpResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ServerResponse handle(ClientRequest clientRequest) {

        responseBuilder.buildHttpVersion();
        responseBuilder.buildOkStatus();
        return responseBuilder.getResponse();
    }
}
