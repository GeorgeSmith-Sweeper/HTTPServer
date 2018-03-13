package com.GeorgesServer.app.com.GeorgesServer.response;


public interface IResponseBuilder {
    void setStatusCode(String statusCode);
    void setStatusMsg(String statusMsg);
    void setHttpVersion(String httpVersion);
    void buildOkStatus();
    void build206Status();
    void buildHttpVersion();
    void buildBody(String body);
    void buildContentRangeHeader(String start, String end);

    ServerResponse getResponse();

    void buildContentLengthHeader(int length);
}
