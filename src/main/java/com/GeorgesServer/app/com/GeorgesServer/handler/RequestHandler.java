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

        responseBuilder.buildOkStatus();
        return responseBuilder.getResponse();

//        if (clientRequest.getUrl().equals("/") || clientRequest.getUrl().equals("/form")) {
//            return clientRequest.getHttpVersion() + " " + StatusCodes.OK + "\n";
//        }
////
//        if (clientRequest.getMethod().equals("HEAD") && clientRequest.getUrl().equals("/foobar")) {
//            return clientRequest.getHttpVersion() + " " + StatusCodes.NOT_FOUND + "\n";
//        }

//        return "";
    }
}
