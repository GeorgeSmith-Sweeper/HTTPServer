package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class FormHandler implements IHandler {

    private HttpResponseBuilder responseBuilder;
    private ServerResponse serverResponse;

    public FormHandler(HttpResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
      if (clientRequest.getMethod().equals("GET")) {
          responseBuilder.buildHttpVersion();
          responseBuilder.buildOkStatus();
          serverResponse = responseBuilder.getResponse();
      }

      return serverResponse;
    }
}
