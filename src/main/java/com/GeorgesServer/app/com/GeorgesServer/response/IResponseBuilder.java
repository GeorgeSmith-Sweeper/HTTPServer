package com.GeorgesServer.app.com.GeorgesServer.response;

import java.util.ArrayList;

public interface IResponseBuilder {
    void setStatusCode(String statusCode);
    void setStatusMsg(String statusMsg);
    void setHttpVersion(String httpVersion);
    void buildOkStatus();
    void build206Status();
    void buildHttpVersion();
    void buildContentRangeHeader();

    ServerResponse getResponse();

}
