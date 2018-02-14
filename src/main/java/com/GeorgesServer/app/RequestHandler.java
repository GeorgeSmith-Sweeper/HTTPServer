package com.GeorgesServer.app;

import java.util.HashMap;

public class RequestHandler {
    public String handle(HashMap<String, String> clientRequest) {
        if (clientRequest.get("Method").equals("GET") && clientRequest.get("Url").equals("/")) {
            return clientRequest.get("HttpVersion") + " 200 OK" + "\n";
        }
        return "";
    }
}
