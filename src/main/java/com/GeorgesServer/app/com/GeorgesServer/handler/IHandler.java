package com.GeorgesServer.app.com.GeorgesServer.handler;

import java.util.HashMap;

public interface IHandler {
    void handle();
    String getStatus();
    HashMap<String, String> getHeaders();
    String getBody();
    String format();
}
