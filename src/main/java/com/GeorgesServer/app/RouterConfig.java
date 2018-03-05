package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.IResponseBuilder;

import java.util.HashMap;

public class RouterConfig {

    private HttpResponseBuilder responseBuilder;
    DefaultHandler defaultHandler;
    FormHandler formHandler;
    Router router;

    public RouterConfig(HttpResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
        makeHandlers(responseBuilder);
        router = new Router();
        make();
    }

    public void makeHandlers(HttpResponseBuilder responseBuilder) {
        defaultHandler = new DefaultHandler(responseBuilder);
        formHandler = new FormHandler(responseBuilder);
    }

    public void make() {
        router.addRoute("/", defaultHandler);
        router.addRoute("/form", formHandler);
    }

    public Router getRouter() {
        return router;
    }
}
