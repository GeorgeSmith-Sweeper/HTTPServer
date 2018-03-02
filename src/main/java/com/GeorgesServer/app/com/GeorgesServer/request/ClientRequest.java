package com.GeorgesServer.app.com.GeorgesServer.request;

public class ClientRequest implements IRequestBuilder {
    private String httpVersion;
    private String method;
    private String url;

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
}
