package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DirectoryHandler implements IHandler {

    private String publicFolderPath;
    private Path path;
    private String status;
    private String body;
    private HashMap<String, String> headers;
    private File[] files;

    public DirectoryHandler(String publicFolderPath, ClientRequest request) {
        this.path = Paths.get(publicFolderPath + request.getUrl());
        this.publicFolderPath = publicFolderPath;
    }

    @Override
    public void handle() {
        setFiles();
        setStatus();
        setHeaders();
        setBody();
    }

    private void setFiles() {
        this.files = path.toFile().listFiles();
    }

    public File[] getFiles() {
        return files;
    }

    public String createLinks() {
        StringBuilder links = new StringBuilder();

        for (File file : files) {
            String relativePath = file.getPath().split(publicFolderPath)[1];
            links.append("<a href=\"/" + relativePath + "\">" + file.getName() + "</a><br>");
        }
        return links.toString();
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

    public void setBody () {
        this.body = createLinks();
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
        response.append("\n").append(getBody());
        return response.toString();
    }

}
