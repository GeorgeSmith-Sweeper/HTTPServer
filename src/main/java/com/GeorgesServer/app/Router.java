package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.*;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Router {

    public IHandler route(String publicFolderPath, ClientRequest request) {
        Path path = Paths.get(publicFolderPath + request.getUrl());

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
        if (Files.exists(path)) {
            return new FilesHandler(publicFolderPath, request);
        }
        if (request.getUrl().equals("/logs")) {
            return new AuthHandler();
        }
        return new DefaultHandler();
    }
}
