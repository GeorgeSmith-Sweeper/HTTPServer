package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class RedirectHandler implements IHandler {
    private String publicFolderPath;
    private ClientRequest request;
    private String status;
    private HashMap<String,String> headers;

    public RedirectHandler(String publicFolderPath, ClientRequest request) {
        this.publicFolderPath = publicFolderPath;
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setHeaders();
    }

    private void setStatus() {
        this.status = StatusCodes.FOUND;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    private void setHeaders() {
        headers = new HashMap<>();
        headers.put("Location", "/");
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
            response.append(key).append(":").append(value).append("\n");
        }
        response.append("\n").append(getBody());
        return response.toString();
    }
}
