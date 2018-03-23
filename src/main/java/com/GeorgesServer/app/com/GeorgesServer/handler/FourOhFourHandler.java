package com.GeorgesServer.app.com.GeorgesServer.handler;


import com.GeorgesServer.app.StatusCodes;

import java.util.HashMap;

public class FourOhFourHandler implements IHandler{
    private String status;

    @Override
    public void handle() {
        setStatus();
    }

    private void setStatus() {
        this.status = StatusCodes.NOT_FOUND;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getBody() {
        return "";
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        return response.toString();
    }
}
