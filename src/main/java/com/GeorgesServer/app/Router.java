package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class Router {

    private DefaultHandler defaultHandler;

    public Router(DefaultHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public IHandler route(String method, String url) {

        IHandler handler = (method.equals("/") && url.equals("GET")) ? defaultHandler : null;
        return handler;
    }
}
