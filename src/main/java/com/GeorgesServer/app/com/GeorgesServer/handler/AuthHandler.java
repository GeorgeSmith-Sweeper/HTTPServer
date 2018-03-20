package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class AuthHandler implements IHandler{

    private final String requestBody;
    private final HashMap<String, String> requestHeaders;
    private final String requestMethod;
    private final String requestUrl;
    private HashMap<String, String> headers;
    private String status;
    private String body;


    public AuthHandler(ClientRequest request) {
        this.requestBody = request.getBody();
        this.requestHeaders = request.getHeaders();
        this.requestMethod = request.getMethod();
        this.requestUrl = request.getUrl();
    }

    @Override
    public void handle() {
        setHeaders();
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    private void setHeaders() {
        headers = new HashMap<>();
        if (!requestHeaders.containsKey("Authorization")) {
            headers.put("WWW-Authenticate", "Basic realm=WallyWorld");
            this.status = "HTTP/1.1 401 Unauthorized";
        }
    }
    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(":").append(value).append("\n");
        }
        return response.toString();
    }
}
