package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;

public class RequestParser {

    private RequestReader requestReader;
    private String method;
    private String url;
    private String httpVersion;
    private ClientRequest clientRequest;
    private ArrayList<String> headers;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
        clientRequest = new ClientRequest();
    }

    public ClientRequest parse(InputStreamReader reader) {
        String request = requestReader.read(reader);
        parseRequestStartLine(request);
        parseHeaders(request);
        clientRequest.setMethod(getMethod());
        clientRequest.setUrl(getUrl());
        clientRequest.setHttpVersion(getHttpVersion());
        clientRequest.setHeaders(getHeaders());
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
}
