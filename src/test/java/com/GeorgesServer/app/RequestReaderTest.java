package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestReaderTest {
    private InputStream mockedInputStream;
    private RequestReader subject;

    @BeforeEach
    public void setUp() {
        mockedInputStream = mock(InputStream.class);
        subject = new RequestReader();
    }

    @Test
    void requestReaderReturnsAStringFromAInputStreamReader() throws IOException {
        int request = 71;
        int streamHasNotEnded = -1;

        when(mockedInputStream.read()).thenReturn(request).thenReturn(streamHasNotEnded);
        when(mockedInputStream.available()).thenReturn(1);

        String result = subject.read(mockedInputStream);

        assertEquals("G", result);
    }
}