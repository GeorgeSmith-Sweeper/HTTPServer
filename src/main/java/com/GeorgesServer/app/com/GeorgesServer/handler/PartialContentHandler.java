package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class PartialContentHandler implements IHandler{

    private HttpResponseBuilder responseBuilder;
    private ServerResponse serverResponse;

    public PartialContentHandler() {
        responseBuilder = new HttpResponseBuilder();
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
        responseBuilder.buildHttpVersion();
        responseBuilder.build206Status();
        return responseBuilder.getResponse();
    }
}
