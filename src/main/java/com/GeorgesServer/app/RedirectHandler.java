package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class RedirectHandler implements IHandler {
    private String publicFolderPath;
    private ClientRequest request;
    private String status;

    public RedirectHandler(String publicFolderPath, ClientRequest request) {
        this.publicFolderPath = publicFolderPath;
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
    }

    private void setStatus() {
        this.status = "HTTP/1.1 302 Found";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public String format() {
        return null;
    }
}
