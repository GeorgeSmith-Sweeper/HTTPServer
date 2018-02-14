package com.GeorgesServer.app;

import java.util.HashMap;

public class RequestHandler {
    public String handle(HashMap<String, String> clientRequest) {

        // SIMPLE POST
        if (clientRequest.get("Method").equals("POST") && clientRequest.get("Url").equals("/form")) {
            return clientRequest.get("HttpVersion") + " 200 OK" + "\n";
        }

        // SIMPLE GET
        if (clientRequest.get("Method").equals("GET") && clientRequest.get("Url").equals("/")) {
            return clientRequest.get("HttpVersion") + " 200 OK" + "\n";
        }
        return null;
    }
}
