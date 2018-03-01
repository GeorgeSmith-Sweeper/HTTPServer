package com.GeorgesServer.app;

public class ServerResponse {
    private String status;
    
    public String format() {
        return null;
    }

    public void setStatus(String statusCode, String statusMessage) {
        this.status = statusCode + " " + statusMessage;
    }

    public String getStatus() {
        return status;
    }

}
