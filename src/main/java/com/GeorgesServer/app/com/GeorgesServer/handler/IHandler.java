package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

import java.util.HashMap;

public interface IHandler {
    void handle(ClientRequest clientRequest);
    String getStatus();
    HashMap<String, String> getHeaders();
    String getBody();
    String format();
}
