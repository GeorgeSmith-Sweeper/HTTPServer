package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.util.HashMap;

public class RequestParser {

    private RequestReader requestReader;
    private String method;
    private String url;
    private String httpVersion;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
    }

    public HashMap<String, String> parse(BufferedReader reader) {
        HashMap<String, String> parsedRequest = new HashMap<>();
        String request = requestReader.read(reader);

        parseRequestStartLine(request);
        parsedRequest.put("Method", getMethod());
        parsedRequest.put("Url", getUrl());
        parsedRequest.put("HttpVersion", getHttpVersion());
        return parsedRequest;
    }
    
    private void parseRequestStartLine(String request) {
        String[] splitRequest = request.split(" ");
        this.method = splitRequest[0];
        this.url = splitRequest[1];
        this.httpVersion = splitRequest[2];
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
}
