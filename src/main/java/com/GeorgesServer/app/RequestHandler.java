package com.GeorgesServer.app;

import java.util.HashMap;


public class RequestHandler {
    StringBuilder sb = new StringBuilder();

    public String handle(HashMap<String, String> clientRequest) {


        if (clientRequest.get("Method").equals("GET") && clientRequest.get("Url").equals("/")) {
            return clientRequest.get("HttpVersion") + " " + StatusCodes.OK + "\n";
        }

        if (clientRequest.get("Method").equals("HEAD") && clientRequest.get("Url").equals("/")) {
            return clientRequest.get("HttpVersion") + " " + StatusCodes.OK + "\n";
        }

        if (clientRequest.get("Method").equals("POST") && clientRequest.get("Url").equals("/form")) {
            return clientRequest.get("HttpVersion") + " " + StatusCodes.OK+ "\n";
        }

        if (clientRequest.get("Method").equals("PUT") && clientRequest.get("Url").equals("/form")) {
            return clientRequest.get("HttpVersion") + " " + StatusCodes.OK + "\n";
        }

        if (clientRequest.get("Method").equals("HEAD") && clientRequest.get("Url").equals("/foobar")) {
            return clientRequest.get("HttpVersion") + " " + StatusCodes.NOT_FOUND + "\n";
        }

        return "";
    }


}
