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
        setStatus("HTTP/1.1 200 OK");
    }
    
    public void setStatus(String status) {
        this.status = status;
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
