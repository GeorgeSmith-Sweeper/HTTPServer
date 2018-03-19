package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FilesHandler implements IHandler{

    private Path path;
    private ClientRequest request;
    private String status;
    private String body;
    private HashMap<String, String> headers;

    public FilesHandler(String publicFolderPath, ClientRequest request) {
        this.path = Paths.get(publicFolderPath + request.getUrl());
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setBody();
        setHeaders();
    }

    private void setHeaders() {
        headers = new HashMap<>();
    }

    private void setBody() {
        String fileContents = "";
        try {
            fileContents = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.body = fileContents;
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
        response.append("\n").append(getBody());
        return response.toString();
    }
}
