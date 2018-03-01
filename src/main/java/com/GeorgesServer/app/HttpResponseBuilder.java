package com.GeorgesServer.app;

public class HttpResponseBuilder {
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
}
