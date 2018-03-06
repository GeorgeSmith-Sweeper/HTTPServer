package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class Router {

    private HashMap<String, IHandler> routes;

    public Router() {
        routes = new HashMap<>();
    }

    public IHandler route(ClientRequest request) {
        if (routes.get(request.getUrl()) == null) {
           return routes.get("/");
        }
        return routes.get(request.getUrl());
    }

    public void addRoute(String path, IHandler handler) {
        routes.put(path, handler);
    }
}
