package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RequestParserTest {
    private RequestParser subject;
    private RequestReader mockedRequestReader;
    private Connections mockedConnections;
    private BufferedReader mockedBufferReader;

    @BeforeEach
    public void setUp() {
        mockedRequestReader = mock(RequestReader.class);
        mockedConnections = mock(Connections.class);
        mockedBufferReader = mock(BufferedReader.class);
        subject = new RequestParser(mockedRequestReader);
    }

    @Test
    void requestParserUtilizesRequestReaderToReadInputStreams() {
        when(mockedRequestReader.read(mockedConnections.getOut())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getOut());

        verify(mockedRequestReader).read(mockedConnections.getOut());
    }

    @Test
    void requestParserCanParseTheMethodOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getOut())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getOut());

        assertEquals("GET", subject.getMethod());
    }

    @Test
    void requestParserCanParseTheURLOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getOut())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getOut());

        assertEquals("/", subject.getUrl());
    }

    @Test
    void requestParserCanParseTheHTTPVersionOfARequestStartline() {
        when(mockedRequestReader.read(mockedConnections.getOut())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedConnections.getOut());

        assertEquals("HTTP/1.1", subject.getHttpVersion());
    }
}
