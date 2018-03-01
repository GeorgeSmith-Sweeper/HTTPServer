package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.ClientRequest;
import com.GeorgesServer.app.ServerResponse;

public interface IHandler {
    ServerResponse handle(ClientRequest clientRequest);
}
