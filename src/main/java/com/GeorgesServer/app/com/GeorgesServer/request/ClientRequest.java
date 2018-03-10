package com.GeorgesServer.app.com.GeorgesServer.request;

import java.util.ArrayList;

public class ClientRequest {

    private String httpVersion;
    private String method;
    private String url;
    private ArrayList<String> headers;

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }
}
