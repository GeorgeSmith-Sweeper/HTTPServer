package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RequestParserTest {
    private RequestParser subject;
    private RequestReader mockedRequestReader;
    private Connections mockedConnections;

    @BeforeEach
    public void setUp() {
        mockedRequestReader = mock(RequestReader.class);
        mockedConnections = mock(Connections.class);
        subject = new RequestParser(mockedRequestReader);
    }

    @Test
    void requestParserCanParseTheRequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getIn())).thenReturn("GET / HTTP/1.1\n");
        subject.parse(mockedConnections.getIn());

        assertEquals("GET", subject.getMethod());
        assertEquals("/", subject.getUrl());
        assertEquals("HTTP/1.1", subject.getHttpVersion());
    }


    @Test
    void extractStartLineCanGrabTheRequestStartline() {
        String request = "GET / HTTP/1.1\nRandom: header";

        String result = subject.extractStartLine(request);

        assertEquals("GET / HTTP/1.1", result);
    }

}
