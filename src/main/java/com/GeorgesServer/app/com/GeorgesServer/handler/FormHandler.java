package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class FormHandler implements IHandler {

    private HttpResponseBuilder responseBuilder;
    private ServerResponse serverResponse;

    public FormHandler() {
        responseBuilder = new HttpResponseBuilder();
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
      if (clientRequest.getMethod().equals("GET")) {
          responseBuilder.buildHttpVersion();
          responseBuilder.buildOkStatus();
          serverResponse = responseBuilder.getResponse();
      }

      if (clientRequest.getMethod().equals("POST")) {
          responseBuilder.buildHttpVersion();
          responseBuilder.buildOkStatus();
          serverResponse = responseBuilder.getResponse();
      }

      if (clientRequest.getMethod().equals("PUT")) {
          responseBuilder.buildHttpVersion();
          responseBuilder.buildOkStatus();
          serverResponse = responseBuilder.getResponse();
      }

      return serverResponse;
    }
}
