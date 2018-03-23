package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class ParameterHandler implements IHandler {

    private String publicFolderPath;
    private ClientRequest request;
    private String status;
    private String[] queries;
    private HashMap<String, String> parameters;
    private String body;
    private HashMap<String,String> headers;

    public ParameterHandler(String publicFolderPath, ClientRequest request) {
        this.publicFolderPath = publicFolderPath;
        this.request = request;
    }

    @Override
    public void handle() {
        setStatus();
        setQueries();
        parseParameters();
        setBody();
    }


    private void setStatus() {
        this.status = StatusCodes.OK;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    private void setQueries() {
        int questionMarkLocation = request.getUrl().indexOf("?");
        String subRequest = request.getUrl().substring(questionMarkLocation+1);
        queries = subRequest.split("&");
    }

    public String[] getQueries() {
        return queries;
    }

    private void parseParameters() {
        parameters = new HashMap<>();
        try {
            for (String query : queries) {
                String[] splitQuery = query.split("=");
                String decodedValue= URLDecoder.decode(splitQuery[1], "UTF-8");
                parameters.put(splitQuery[0], decodedValue);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,String> getParameters() {
        return parameters;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    private void setBody() {
        StringBuilder content = new StringBuilder();
        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            content.append(key).append(" = ").append(value);
        }
        this.body = content.toString();
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        response.append("\n").append(body);
        return response.toString();
    }
}
