package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class PostHandler implements IHandler {
    private String publicFolderPath;
    private ClientRequest clientRequest;
    private String status;
    private HashMap<String,String> headers;
    private String body;

    public PostHandler(String publicFolderPath, ClientRequest clientRequest) {
        this.publicFolderPath = publicFolderPath;
        this.clientRequest = clientRequest;
    }

    @Override
    public void handle() {
        setStatus();
        setBody();
        setHeaders();
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

    public void setHeaders() {
        headers = new HashMap<>();
        headers.put("Content-Length", Integer.toString(clientRequest.getBody().length()));
    }

    @Override
    public String getBody() {
        return this.body;
    }

    public void setBody() {
       this.body = clientRequest.getBody();
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(": ").append(value).append("\n");
        }
        response.append("\n").append(getBody());
        System.out.println(response.toString());
        return response.toString();
    }
}
