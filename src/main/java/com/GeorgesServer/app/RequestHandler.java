package com.GeorgesServer.app;

import java.util.HashMap;

public class RequestHandler {
    public String handle(HashMap<String, String> clientRequest) {

        String defaultResponse = clientRequest.get("HttpVersion") + " 200 OK" + "\n";

        if (clientRequest.get("Method").equals("POST") && clientRequest.get("Url").equals("/form")) {
            return clientRequest.get("HttpVersion") + " 200 OK" + "\n";
        }

        if (clientRequest.get("Method").equals("GET") && clientRequest.get("Url").equals("/")) {
            return clientRequest.get("HttpVersion") + " 200 OK" + "\n";
        }

        return defaultResponse;
    }
}
