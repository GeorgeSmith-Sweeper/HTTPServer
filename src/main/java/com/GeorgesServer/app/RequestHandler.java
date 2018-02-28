package com.GeorgesServer.app;


public class RequestHandler implements IHandler {

    public String handle(ClientRequest clientRequest) {


//        responseBuilder.build(clientRequest);
//        return response.getResponse();
        if (clientRequest.getUrl().equals("/") || clientRequest.getUrl().equals("/form")) {
            return clientRequest.getHttpVersion() + " " + StatusCodes.OK + "\n";
        }

        if (clientRequest.getMethod().equals("HEAD") && clientRequest.getUrl().equals("/foobar")) {
            return clientRequest.getHttpVersion() + " " + StatusCodes.NOT_FOUND + "\n";
        }

        return "";
    }
}
