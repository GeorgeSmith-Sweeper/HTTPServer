package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class FilesHandler implements IHandler{

    public FilesHandler(String publicFolderPath, ClientRequest request) {
    }

    @Override
    public void handle() {

    }

    @Override
    public String getStatus() {
        return null;
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
