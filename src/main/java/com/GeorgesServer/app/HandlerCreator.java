package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;

import java.util.HashMap;

public class HandlerCreator {
    private HashMap<String, IHandler> handlers = new HashMap<>();
    private String publicFolderPath;

    public HandlerCreator(String publicFolderPath) {
        this.publicFolderPath = publicFolderPath;
        makeHandlers();
    }

    public void makeHandlers() {
        handlers.put("defaultHandler", new DefaultHandler());
        handlers.put("formHandler", new FormHandler());
        handlers.put("partialContentHandler", new PartialContentHandler());
    }

    public HashMap<String, IHandler>getHandlers() {
        return handlers;
    }
}
