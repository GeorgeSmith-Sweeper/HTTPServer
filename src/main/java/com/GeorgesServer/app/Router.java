package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class Router {

    private HashMap<String, IHandler> routes;

    public Router() {
        routes = new HashMap<>();
    }

    public IHandler route(String publicFolderPath, ClientRequest request) {
        if (isPartialContent(request.getUrl())) {
            return new PartialContentHandler(publicFolderPath, request);
        }
        return routes.get(request.getUrl());
    }

    public void addRoute(String path, IHandler handler) {
        routes.put(path, handler);
    }

    private boolean isPartialContent(String path) {
        return path.equals("/partial_content.txt");
    }
}
