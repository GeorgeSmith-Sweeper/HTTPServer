package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.util.HashMap;

public class RequestParser {

    private RequestReader requestReader;
    private String convertedRequest;
    private String method;
    private String url;
    private String httpVersion;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
    }

    public HashMap<String, String> parse(BufferedReader reader) {
        convertedRequest = requestReader.read(reader);
        setMethod();
        setUrl();
        setHttpVersion();

        HashMap<String, String> parsedRequest = new HashMap<>();
        parsedRequest.put("Method", getMethod());
        parsedRequest.put("Url", getUrl());
        parsedRequest.put("HttpVersion", getHttpVersion());
        return parsedRequest;
    }

    public void setMethod() {
        String[] splitRequest = convertedRequest.split(" ");
        this.method = splitRequest[0];
    }

    public String getMethod() {
        return method;
    }

    private void setUrl() {
        String[] splitRequest = convertedRequest.split(" ");
        this.url = splitRequest[1];
    }

    public String getUrl() {
        return url;
    }

    private void setHttpVersion() {
        String[] splitRequest = convertedRequest.split(" ");
        this.httpVersion = splitRequest[2];
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
