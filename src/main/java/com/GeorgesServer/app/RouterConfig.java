package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;

import java.util.HashMap;

public class RouterConfig {

    private HashMap<String, IHandler> handlers;
    private Router router;

    public RouterConfig(HashMap<String, IHandler> handlers) {
        this.handlers = handlers;
        router = new Router();
        makeRoutes(router);
    }

    public void makeRoutes(Router router) {
        router.addRoute("/", handlers.get("defaultHandler"));
        router.addRoute("/form", handlers.get("formHandler"));
        router.addRoute("/partial_content.txt", handlers.get("partialContentHandler"));
    }

    public Router getRouter() {
        return router;
    }
}
