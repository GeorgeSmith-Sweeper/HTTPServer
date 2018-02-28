package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.ClientRequest;

public interface IHandler {
    String handle(ClientRequest clientRequest);
}
