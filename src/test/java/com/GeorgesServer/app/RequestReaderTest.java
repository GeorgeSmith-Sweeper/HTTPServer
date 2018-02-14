package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestReaderTest {
    private BufferedReader mockedBufferedReader;
    private RequestReader subject;

    @BeforeEach
    public void setUp() {
        mockedBufferedReader = mock(BufferedReader.class);
        subject = new RequestReader();
    }

    @Test
    void requestReaderReturnsAStringFromABufferedReader() throws IOException {
        String request = "GET / HTTP/1.1\n";
        when(mockedBufferedReader.readLine()).thenReturn(request);

        String result = subject.read(mockedBufferedReader);

        assertEquals("GET / HTTP/1.1\n", result);
    }

    @Test
    void requestReaderReturnsMultipleLinesFromABufferedReader() throws IOException {
        String request = "GET / HTTP/1.1\nAnother: Thing";
        when(mockedBufferedReader.readLine()).thenReturn(request);

        String result = subject.read(mockedBufferedReader);

        assertEquals("GET / HTTP/1.1\nAnother: Thing", result);
    }
}