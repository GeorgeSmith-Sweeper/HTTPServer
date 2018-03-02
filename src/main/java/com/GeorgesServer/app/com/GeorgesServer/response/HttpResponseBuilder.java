package com.GeorgesServer.app.com.GeorgesServer.response;

public class HttpResponseBuilder implements IResponseBuilder {
    private ServerResponse response;

    public HttpResponseBuilder() {
        response = new ServerResponse();
    }

    public ServerResponse getResponse() {
        return this.response;
    }

    public void buildOkStatus() {
        this.response.setStatus("200", "OK");
    }

    public void buildHttpVersion() {
        this.response.setHttpVersion("HTTP/1.1");
    }
}
