package com.GeorgesServer.app;

import static org.mockito.Mockito.*;

import com.GeorgesServer.app.com.GeorgesServer.handler.IHandler;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServerTest {

    private StreamMaker mockedStreamMaker;
    private Streams mockedStreams;
    private RequestParser mockedRequestParser;
    private ClientRequest mockedClientRequest;
    private IHandler mockedHandler;
    private ResponseSender mockedResponseSender;
    private ServerResponse mockedServerResponse;
    private String publicFolderPath;
    private String mockedFormattedResponse;
    private Router mockedRouter;
    private RouterConfig mockedRouterConfig;

    @BeforeEach
    public void setUp() {
        mockedStreamMaker = mock(StreamMaker.class);
        mockedHandler = mock(IHandler.class);
        mockedClientRequest = mock(ClientRequest.class);
        mockedStreams = mock(Streams.class);
        mockedRequestParser = mock(RequestParser.class);
        mockedResponseSender = mock(ResponseSender.class);
        mockedServerResponse = mock(ServerResponse.class);
        mockedRouter = mock(Router.class);
        mockedRouterConfig = mock(RouterConfig.class);
        mockedFormattedResponse = "";
        publicFolderPath = "";
    }
    
    @Test
    public void startServerCallsTheCorrectMethods() {
        MyServer myServer = new MyServer(
                mockedStreamMaker,
                mockedRequestParser,
                mockedResponseSender,
                mockedRouterConfig,
                publicFolderPath);

        when(mockedRouterConfig.getRouter()).thenReturn(mockedRouter);
        when(mockedStreamMaker.connect()).thenReturn(mockedStreams);
        when(mockedRequestParser.parse(mockedStreams.getIn())).thenReturn(mockedClientRequest);
        when(mockedRouter.route(mockedClientRequest)).thenReturn(mockedHandler);
        when(mockedHandler.handle(mockedClientRequest)).thenReturn(mockedServerResponse);
        when(mockedServerResponse.format()).thenReturn(mockedFormattedResponse).thenReturn("Bye");

        myServer.start();

        verify(mockedRouterConfig, atLeastOnce()).getRouter();
        verify(mockedStreamMaker, atLeastOnce()).connect();
        verify(mockedRequestParser, atLeastOnce()).parse(mockedStreams.getIn());
        verify(mockedRouter, atLeastOnce()).route(mockedClientRequest);
        verify(mockedHandler, atLeastOnce()).handle(mockedClientRequest);
        verify(mockedServerResponse, atLeastOnce()).format();
        verify(mockedResponseSender, atLeastOnce()).send(mockedFormattedResponse, mockedStreams.getOut());
    }
}
