package com.GeorgesServer.app.com.GeorgesServer.handler;


import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class OptionsHandler implements IHandler {

    private ClientRequest request;
    private String status;
    private HashMap<String,String> headers;

    public OptionsHandler(ClientRequest request) {
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setHeaders();
    }

    private void setStatus() {
        this.status = "HTTP/1.1 200 OK";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    public void setHeaders() {
        headers = new HashMap<>();
        if (request.getUrl().equals("/method_options2")) {
            headers.put("Allow", "GET,OPTIONS");
        } else {
            headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        }
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return "";
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(": ").append(value).append("\n");
        }
        System.out.println(response.toString());
        return response.toString();
    }
}
