package com.GeorgesServer.app.com.GeorgesServer.request;

public interface IRequestBuilder {
    void setHttpVersion(String httpVersion);
    void setMethod(String method);
    void setUrl(String url);
    String getUrl();
    String getHttpVersion();
    String getMethod();
}
