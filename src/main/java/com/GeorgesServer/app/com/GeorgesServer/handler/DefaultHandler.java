package com.GeorgesServer.app.com.GeorgesServer.handler;


import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class DefaultHandler implements IHandler {

    private HttpResponseBuilder responseBuilder;

    public DefaultHandler(HttpResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ServerResponse handle(ClientRequest clientRequest) {


        responseBuilder.buildHttpVersion();
        responseBuilder.buildOkStatus();
        return responseBuilder.getResponse();
    }
}
