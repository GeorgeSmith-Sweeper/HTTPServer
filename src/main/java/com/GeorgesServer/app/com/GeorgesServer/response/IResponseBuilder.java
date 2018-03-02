package com.GeorgesServer.app.com.GeorgesServer.response;

public interface IResponseBuilder {
    void setStatusCode(String statusCode);
    void setStatusMsg(String statusMsg);
    void setHttpVersion(String httpVersion);
    void buildOkStatus();
    void buildHttpVersion();
    ServerResponse getResponse();
}
