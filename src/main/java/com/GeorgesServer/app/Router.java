package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

public class Router {

    private IHandler defaultHandler;
    private IHandler formHandler;
    private IHandler handler;

    public Router(IHandler defaultHandler, IHandler formHandler) {
        this.defaultHandler = defaultHandler;
        this.formHandler = formHandler;
    }

    public IHandler route(ClientRequest request) {

        if (request.getMethod().equals("GET") && request.getUrl().equals("/")) {
            return defaultHandler;
        }

        if (request.getUrl().equals("/form")) {
            return formHandler;
        }

        return handler;
    }
}
