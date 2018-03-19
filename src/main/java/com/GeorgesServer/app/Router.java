package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.*;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.util.HashMap;

public class Router {

    private HashMap<String, IHandler> routes;

    public Router() {
        routes = new HashMap<>();
    }

    public IHandler route(String publicFolderPath, ClientRequest request) {
        if (request.getHeaders().containsKey("Range")) {
            return new PartialContentHandler(publicFolderPath, request);
        }
        if (request.getMethod().equals("POST")) {
            return new PostHandler(publicFolderPath, request);
        }
        if (request.getMethod().equals("OPTIONS")) {
            return new OptionsHandler(request);
        }
        if (request.getUrl().equals("/foobar") && !request.getMethod().isEmpty()) {
            return new FourOhFourHandler();
        }
        return new DefaultHandler();
    }
}
