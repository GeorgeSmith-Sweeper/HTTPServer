package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class FormHandler implements IHandler {

    private HttpResponseBuilder responseBuilder;

    public FormHandler(HttpResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
      return null;
    }
}
