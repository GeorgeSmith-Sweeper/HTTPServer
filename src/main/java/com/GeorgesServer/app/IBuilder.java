package com.GeorgesServer.app;

public interface IBuilder {
    void buildOkStatus();
    void buildHttpVersion();
    ServerResponse getResponse();
}
