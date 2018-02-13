package com.GeorgesServer.app;

import java.io.BufferedReader;

public class RequestParser {

    private RequestReader requestReader;
    private String convertedRequest;
    private String method;
    private String url;
    private String httpVersion;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
    }

    public ClientRequest parse(BufferedReader reader) {
        convertedRequest = requestReader.read(reader);
//        System.out.println(convertedRequest);
        setMethod();
        setUrl();
        setHttpVersion();
        return null;
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
