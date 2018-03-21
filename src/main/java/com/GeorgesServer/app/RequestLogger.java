package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.ArrayList;

public class RequestLogger {

    private ArrayList<String> logs;

    public RequestLogger() {
        this.logs = new ArrayList<>();
    }

    public void log(ClientRequest clientRequest) {
        String request = clientRequest.getMethod() + " " +
                clientRequest.getUrl() + " " +
                clientRequest.getHttpVersion();
        logs.add(request);
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }
}
