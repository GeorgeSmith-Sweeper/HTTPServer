package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RequestParserTest {
    private RequestParser subject;
    private RequestReader mockedRequestReader;
    private Streams mockedStreams;

    @BeforeEach
    public void setUp() {
        mockedRequestReader = mock(RequestReader.class);
        mockedStreams = mock(Streams.class);
        subject = new RequestParser(mockedRequestReader);
    }

    @Test
    void requestParserCanParseTheRequestStartline() {
        when(mockedRequestReader.read(mockedStreams.getIn())).thenReturn("GET / HTTP/1.1");
        subject.parse(mockedStreams.getIn());

        assertEquals("GET", subject.getMethod());
        assertEquals("/", subject.getUrl());
        assertEquals("HTTP/1.1", subject.getHttpVersion());
        assertEquals("GET / HTTP/1.1", subject.getStartLine());
    }

    @Test
    void requestParserCanParseTheRequestHeaders() {
        when(mockedRequestReader.read(mockedStreams.getIn())).thenReturn("GET / HTTP/1.1\n" + "Range: bytes=1-4\n");
        subject.parse(mockedStreams.getIn());

        assertEquals(" bytes=1-4", subject.getHeaders().get("Range"));
    }

    @Test
    void requestParserCanParseTheRequestBody() {
        String body = "\"My\"=\"Data\"";
        when(mockedRequestReader.read(mockedStreams.getIn())).thenReturn("POST / HTTP/1.1\n" + "Content-Length: 11\n" + "\n" + body);
        subject.parse(mockedStreams.getIn());

        assertEquals(body, subject.getBody());
    }
}
