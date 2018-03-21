package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.InputStream;
import java.util.HashMap;

public class RequestParser {

    private RequestReader requestReader;
    private String method;
    private String url;
    private String httpVersion;
    private ClientRequest clientRequest;
    private HashMap<String, String> headers;
    private String body;
    private String startLine;

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
        clientRequest.setBody(getBody());
        clientRequest.setStartLine(getStartLine());
        return clientRequest;
    }
    
    private void parseRequestStartLine(String request) {
        String[] splitRequest = request.split(" ");
        String startLine = request.split("\n")[0];
        this.method = splitRequest[0];
        this.url = splitRequest[1];
        this.httpVersion = splitRequest[2];
        this.startLine = startLine;
    }

    private void parseHeaders(String request) {
        String[] lines = request.split("\n");
        headers = new HashMap<>();
        for (String line : lines) {
            if (line.contains(":")) {
                String[] splitHeader = line.split(":");
                headers.put(splitHeader[0], splitHeader[1]);
            }
        }
    }

    private void parseBody(String request) {
        String[] lines = request.split("\n");
        String lastLine = lines[lines.length - 1];
        if (!lastLine.isEmpty()) {
            this.body = lastLine;
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

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getStartLine() {
        return startLine;
    }
}
