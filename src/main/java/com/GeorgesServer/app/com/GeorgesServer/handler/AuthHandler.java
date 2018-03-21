package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.RequestLogger;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.Arrays;
import java.util.HashMap;
import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

public class AuthHandler implements IHandler{

    private ClientRequest request;
    private RequestLogger requestLogger;
    private HashMap<String, String> headers;
    private String status;
    private String body;

    public AuthHandler(ClientRequest request, RequestLogger requestLogger) {
        this.request = request;
        this.requestLogger = requestLogger;
    }

    @Override
    public void handle() {
        setBody();
        setStatus();
        setHeaders();
    }

    private void setStatus() {
        if (isAuthorized()) {
            this.status = "HTTP/1.1 200 OK";
        } else {
            this.status = "HTTP/1.1 401 Unauthorized";
        }
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    private void setHeaders() {
        headers = new HashMap<>();
        if (!isAuthorized()) {
            headers.put("WWW-Authenticate", "Basic realm=WallyWorld");
        }
    }

    public boolean isAuthorized() {
        try {
            String credentials = request.getHeaders().get("Authorization");
            String encoded = credentials.replace("Basic ", "");
            byte[] decoded = parseBase64Binary(encoded);
            return (Arrays.equals("admin:hunter2".getBytes(), decoded));
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    private void setBody() {
        if (isAuthorized()) {
            for (String log : requestLogger.getLogs()) {
                this.body += log + "\n";
            }
        } else {
            this.body = "";
        }
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
