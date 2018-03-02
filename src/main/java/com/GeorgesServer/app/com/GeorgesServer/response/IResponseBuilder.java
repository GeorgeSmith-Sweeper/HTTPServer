package com.GeorgesServer.app.com.GeorgesServer.response;

public interface IResponseBuilder {
    void buildOkStatus();
    void buildHttpVersion();
    ServerResponse getResponse();
}
