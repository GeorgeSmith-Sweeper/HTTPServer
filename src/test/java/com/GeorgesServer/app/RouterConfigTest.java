package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.PartialContentHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RouterConfigTest {
    private HashMap mockedRouterHandlers;
    private Router mockedRouter;
    private String publicFolderPath;

    @BeforeEach
    void setUp() {
        mockedRouterHandlers = mock(HashMap.class);
        mockedRouter = mock(Router.class);
    }

    @Test
    void makeRoutesAssignsAPathToASpecificHandler() {
        IHandler defaultHandler = new DefaultHandler();
        IHandler formHandler = new FormHandler();
        IHandler partialContentHandler = new PartialContentHandler(publicFolderPath);

        RouterConfig subject = new RouterConfig(mockedRouterHandlers);

        when(mockedRouterHandlers.get("defaultHandler")).thenReturn(defaultHandler);
        when(mockedRouterHandlers.get("formHandler")).thenReturn(formHandler);
        when(mockedRouterHandlers.get("partialContentHandler")).thenReturn(partialContentHandler);

        subject.makeRoutes(mockedRouter);

        verify(mockedRouter).addRoute("/", defaultHandler);
        verify(mockedRouter).addRoute("/form", formHandler);
        verify(mockedRouter).addRoute("/partial_content.txt", partialContentHandler);
    }
}