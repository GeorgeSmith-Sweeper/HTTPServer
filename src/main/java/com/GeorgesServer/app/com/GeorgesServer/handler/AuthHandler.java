package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;
import java.util.Base64;
import java.util.logging.Logger;

public class AuthHandler implements IHandler{

    private String publicFolderPath;
    private final String requestBody;
    private final HashMap<String, String> requestHeaders;
    private final String requestMethod;
    private final String requestUrl;
    private HashMap<String, String> headers;
    private String status;
    private String body;

    public AuthHandler(String publicFolderPath, ClientRequest request) {
        this.publicFolderPath = publicFolderPath;
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
            this.body = "";
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
        response.append("\n").append(body);
        return response.toString();
    }
}
