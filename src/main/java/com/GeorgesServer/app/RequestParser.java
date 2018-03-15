package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.InputStream;
import java.util.ArrayList;


public class RequestParser {

    private RequestReader requestReader;
    private String method;
    private String url;
    private String httpVersion;
    private ClientRequest clientRequest;
    private ArrayList<String> headers;
    private String body;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
        clientRequest = new ClientRequest();
    }

    public ClientRequest parse(InputStream reader) {
        String request = requestReader.read(reader);
        parseRequestStartLine(request);
        parseHeaders(request);
        parseBody(request);
        clientRequest.setMethod(getMethod());
        clientRequest.setUrl(getUrl());
        clientRequest.setHttpVersion(getHttpVersion());
        clientRequest.setHeaders(getHeaders());
        clientRequest.setBody(body);
        return clientRequest;
    }
    
    private void parseRequestStartLine(String request) {
        String[] splitRequest = request.split(" ");
        this.method = splitRequest[0];
        this.url = splitRequest[1];
        this.httpVersion = splitRequest[2];
    }

    private void parseHeaders(String request) {
        String[] lines = request.split("\n");
        headers = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(":")) {
                headers.add(line);
            }
        }
    }

    private void parseBody(String request) {
        String[] lines = request.split("\n");
        for (int index = 0; index < lines.length; index++) {
            if (lines[index].isEmpty()) {
                this.body = lines[index+1];
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
