package com.GeorgesServer.app;

public class ServerResponse {
    private String status;
    private String httpVersion;
    
    public String format() {
        return getHttpVersion() +  " " + getStatus() + "\n";
    }

    public void setStatus(String statusCode, String statusMessage) {
        this.status = statusCode + " " + statusMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
