package com.GeorgesServer.app.com.GeorgesServer.response;

public class HttpResponseBuilder implements IResponseBuilder {
    private ServerResponse response;

    public HttpResponseBuilder() {
        response = new ServerResponse();
    }

    public ServerResponse getResponse() {
        return this.response;
    }

    @Override
    public void setStatusCode(String statusCode) {
        this.response.setStatusCode(statusCode);
    }

    @Override
    public void setStatusMsg(String statusMsg) {
        this.response.setStatusMsg(statusMsg);
    }

    @Override
    public void setHttpVersion(String httpVersion) {
        this.response.setHttpVersion(httpVersion);
    }

    public void buildOkStatus() {
        setStatusCode("200");
        setStatusMsg("OK");
    }

    public void buildHttpVersion() {
        setHttpVersion("HTTP/1.1");
    }
}
