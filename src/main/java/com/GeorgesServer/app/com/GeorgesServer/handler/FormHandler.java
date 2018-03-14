package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class FormHandler implements IHandler {

    private String status;
    private HashMap<String,String> headers;
    private String body;
    private HashMap<String, String> methods = new HashMap<>();

    public FormHandler () {
        this.methods.put("GET", "GET");
        this.methods.put("POST", "POST");
        this.methods.put("PUT", "PUT");
    }

    @Override
    public void handle(ClientRequest clientRequest) {
      if (this.methods.containsKey(clientRequest.getMethod())) {
          setStatus();
      }
    }

    private void setStatus() {
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
