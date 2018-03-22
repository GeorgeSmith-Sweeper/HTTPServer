package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DirectoryHandler implements IHandler {

    private Path path;
    private ClientRequest request;
    private String status;
    private String body;
    private HashMap<String, String> headers;
    private File[] files;

    public DirectoryHandler(String publicFolderPath, ClientRequest request) {
        this.path = Paths.get(publicFolderPath + request.getUrl());
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setHeaders();
        setFiles();
    }

    private void setFiles() {
        this.files = path.toFile().listFiles();
    }

    public File[] getFiles() {
        return files;
    }

    private void setStatus() {
        this.status = "HTTP/1.1 200 OK";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    private void setHeaders() {
        headers = new HashMap<>();
        headers.put("Content-Type", "text/html");
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
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(":").append(value).append("\n");
        }
        return response.toString();
    }
}
