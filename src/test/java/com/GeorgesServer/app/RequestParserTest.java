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
    void requestParserUtilizesRequestReaderToReadInputStreams() {
        when(mockedRequestReader.read(mockedConnections.getIn())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getIn());

        verify(mockedRequestReader).read(mockedConnections.getIn());
    }

    @Test
    void requestParserCanParseTheMethodOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getIn())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getIn());

        assertEquals("GET", subject.getMethod());
    }

    @Test
    void requestParserCanParseTheURLOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getIn())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getIn());

        assertEquals("/", subject.getUrl());
    }

    @Test
    void requestParserCanParseTheHTTPVersionOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getIn())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getIn());

        assertEquals("HTTP/1.1", subject.getHttpVersion());
    }
}
