package com.GeorgesServer.app;


public class RequestHandler {

    public String handle(ClientRequest clientRequest) {

        if (clientRequest.getUrl().equals("/") || clientRequest.getUrl().equals("/form")) {
            return clientRequest.getHttpVersion() + " " + StatusCodes.OK + "\n";
        }

        if (clientRequest.getMethod().equals("HEAD") && clientRequest.getUrl().equals("/foobar")) {
            return clientRequest.getHttpVersion() + " " + StatusCodes.NOT_FOUND + "\n";
        }

        return "";
    }


}
