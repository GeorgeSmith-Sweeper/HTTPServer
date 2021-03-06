package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FilesHandler implements IHandler{

    private final String requestBody;
    private final HashMap<String, String> requestHeaders;
    private final String requestMethod;
    private final String requestUrl;
    private Path path;
    private String status;
    private String body;
    private HashMap<String, String> headers;

    public FilesHandler(String publicFolderPath, ClientRequest request) {
        this.requestBody = request.getBody();
        this.requestHeaders = request.getHeaders();
        this.requestMethod = request.getMethod();
        this.requestUrl = request.getUrl();
        this.path = Paths.get(publicFolderPath + this.requestUrl);
    }

    @Override
    public void handle() {
        setStatus();
        setBody();
        setHeaders();
    }

    private void setHeaders() {
        headers = new HashMap<>();
        headers.put("Content-Type", applyContentType());
    }

    private void setBody() {
        String fileContents = "";
        try {
            fileContents = new String(Files.readAllBytes(path), "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.body = fileContents;
    }

    private String applyContentType() {
        return URLConnection.guessContentTypeFromName(requestUrl);
    }


    private void setStatus() {
        this.status = StatusCodes.OK;
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
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(":").append(value).append("\n");
        }
        response.append("\n").append(getBody());
        return response.toString();
    }
}
