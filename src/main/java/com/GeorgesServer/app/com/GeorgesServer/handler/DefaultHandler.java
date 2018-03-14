package com.GeorgesServer.app.com.GeorgesServer.handler;


import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

import java.util.HashMap;

public class DefaultHandler implements IHandler {
    private String body;
    private String status;
    private HashMap<String,String> headers;

    public void handle(ClientRequest clientRequest) {
        setStatus();
    }

    public void setStatus() {
        this.status = "HTTP/1.1 200 OK";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        return response.toString();
    }
}
