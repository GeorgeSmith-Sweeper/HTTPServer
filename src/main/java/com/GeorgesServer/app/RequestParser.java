package com.GeorgesServer.app;

import java.net.Socket;

public class RequestParser {

    private RequestReader requestReader;
    private String convertedRequest;
    private String method;

    public RequestParser(RequestReader requestReader) {
        this.requestReader = requestReader;
    }

    public ClientRequest parse(Socket inStream) {
        convertedRequest = requestReader.read(inStream);
        setMethod();
        return null;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod() {
        String[] splitRequest = convertedRequest.split(" ");
        this.method = splitRequest[0];
    }
}
