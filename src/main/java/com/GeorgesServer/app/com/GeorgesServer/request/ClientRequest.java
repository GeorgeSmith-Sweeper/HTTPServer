package com.GeorgesServer.app.com.GeorgesServer.request;

import java.util.HashMap;

public class ClientRequest {

    private String httpVersion;
    private String method;
    private String url;
    private HashMap<String, String> headers;
    private String body;
    private String startLine;

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

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setStartLine(String startLine) {
        this.startLine = startLine;
    }

    public String getStartLine() {
        return startLine;
    }
}
