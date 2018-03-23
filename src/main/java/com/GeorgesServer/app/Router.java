package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.*;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Router {

    private RequestLogger requestLogger;

    public Router() {
        this.requestLogger = new RequestLogger();
    }

    public IHandler route(String publicFolderPath, ClientRequest request) {
        requestLogger.log(request);

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
        if (request.getUrl().equals("/logs")) {
            return new AuthHandler(request, requestLogger);
        }
        if (request.getUrl().contains("?") && request.getUrl().contains("parameters")) {
            return new ParameterHandler(publicFolderPath, request);
        }
        if (request.getUrl().equals("/redirect")) {
            return new RedirectHandler(publicFolderPath, request);
        }

        Path path = Paths.get(publicFolderPath + request.getUrl());
        if (Files.isDirectory(path)) {
            return new DirectoryHandler(publicFolderPath, request);
        }
        if (Files.exists(path)) {
            return new FilesHandler(publicFolderPath, request);
        }
        return new DefaultHandler();
    }
}
