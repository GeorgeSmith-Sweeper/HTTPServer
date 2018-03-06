package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import java.util.HashMap;

public class HandlerCreator {
    private HttpResponseBuilder responsebuilder;
    private HashMap<String, IHandler> handlers = new HashMap<>();

    public HandlerCreator(HttpResponseBuilder responsebuilder) {
        this.responsebuilder = responsebuilder;
    }

    public void makeHandlers(HttpResponseBuilder responseBuilder) {
        handlers.put("defaultHandler", new DefaultHandler(responseBuilder));
        handlers.put("formHandler", new FormHandler(responseBuilder));
    }

    public HashMap<String, IHandler>getHandlers() {
        return handlers;
    }
}
