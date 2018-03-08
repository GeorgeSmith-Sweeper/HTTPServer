package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.handler.DefaultHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.FormHandler;
import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RouterConfigTest {
    private HashMap mockedRouterHandlers;
    private Router mockedRouter;

    @BeforeEach
    void setUp() {
        mockedRouterHandlers = mock(HashMap.class);
        mockedRouter = mock(Router.class);
    }

    @Test
    void makeRoutesAssignsAPathToASpecificHandler() {
        IHandler defaultHandler = new DefaultHandler();
        IHandler formHandler = new FormHandler();

        RouterConfig subject = new RouterConfig(mockedRouterHandlers);

        when(mockedRouterHandlers.get("defaultHandler")).thenReturn(defaultHandler);
        when(mockedRouterHandlers.get("formHandler")).thenReturn(formHandler);

        subject.makeRoutes(mockedRouter);

        verify(mockedRouter).addRoute("/", defaultHandler);
        verify(mockedRouter).addRoute("/form", formHandler);
    }
}