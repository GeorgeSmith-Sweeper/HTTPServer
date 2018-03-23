package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
        createFile();
        setStatus();
        setBody();
        setHeaders();
    }
    
    public void setStatus() {
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

    public void setHeaders() {
        headers = new HashMap<>();
        headers.put("Location", "http://localhost:5000/" + (publicFolderPath + clientRequest.getUrl()).replace("//", "/"));
    }

    @Override
    public String getBody() {
        return this.body;
    }

    public void setBody() {
       this.body = "";
    }

    private void createFile() {
        try {
            File file = new File(publicFolderPath + clientRequest.getUrl());
            PrintWriter writer = new PrintWriter(file);
            writer.write(formatBody(clientRequest.getBody()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatBody(String body) {
        StringBuilder builder = new StringBuilder();
        if (body.contains("=")) {
            String[] splits = body.split("=", 2);
            builder.append(splits[0] + " = " + splits[1]);
        } else {
            builder.append(body);
        }
        return builder.toString();
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
