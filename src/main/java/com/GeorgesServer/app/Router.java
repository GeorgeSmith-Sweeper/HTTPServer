package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class Router {

    private IHandler defaultHandler;
    private IHandler handler;

    public Router(IHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public IHandler route(String method, String url) {

        handler = (method.equals("GET") && url.equals("/")) ? defaultHandler : null;
        return handler;
    }
}
