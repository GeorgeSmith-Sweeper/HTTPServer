package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {

    private RequestReader requestReader;
    private String method;
    private String url;
    private String httpVersion;
    private ClientRequest clientRequest;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
        clientRequest = new ClientRequest();
    }

    public ClientRequest parse(InputStreamReader reader) {
        String request = requestReader.read(reader);
        System.out.println(request);
        parseRequestStartLine(request);
        clientRequest.setMethod(getMethod());
        clientRequest.setUrl(getUrl());
        clientRequest.setHttpVersion(getHttpVersion());
        return clientRequest;
    }
    
    private void parseRequestStartLine(String request) {
        String[] splitRequest = request.split(" ");
        this.method = splitRequest[0];
        System.out.println("Method: " + method);
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
