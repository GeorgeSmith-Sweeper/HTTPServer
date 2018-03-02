package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

public class Router {

    private IHandler defaultHandler;
    private IHandler formHandler;
    private IHandler handler;

    public Router(IHandler defaultHandler, IHandler formHandler) {
        this.defaultHandler = defaultHandler;
        this.formHandler = formHandler;
    }

    public IHandler route(String method, String url) {

        if (method.equals("GET") && url.equals("/")) {
            return defaultHandler;
        }

        if (url.equals("/form")) {
            return formHandler;
        }

        return handler;
    }
}
