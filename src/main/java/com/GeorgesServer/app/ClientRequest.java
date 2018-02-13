package com.GeorgesServer.app;

import java.util.ArrayList;

public class ClientRequest  {

    private String method;
    private String url;
    private String htmlVersion;

    public ClientRequest(ArrayList<String> fields) {
        method = fields.get(0);
        url = fields.get(1);
        htmlVersion = fields.get(3);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlVersion() {
        return htmlVersion;
    }
}
