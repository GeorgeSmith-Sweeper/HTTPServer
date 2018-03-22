package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class ParameterHandler implements IHandler {

    private String publicFolderPath;
    private ClientRequest request;
    private String status;
    private String[] queries;

    public ParameterHandler(String publicFolderPath, ClientRequest request) {
        this.publicFolderPath = publicFolderPath;
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setQueries();
    }

    private void setStatus() {
        this.status = "HTTP/1.1 200 OK";
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
        return getStatus() + "\n";
    }

    private void setQueries() {
        int questionMarkLocation = request.getUrl().indexOf("?");
        String subRequest = request.getUrl().substring(questionMarkLocation+1);
        queries = subRequest.split("&");
    }

    public String[] getQueries() {
        return queries;
    }
}
