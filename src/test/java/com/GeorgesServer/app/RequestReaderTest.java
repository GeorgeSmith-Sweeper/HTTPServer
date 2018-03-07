package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestReaderTest {
    private InputStreamReader mockedInputStreamReader;
    private RequestReader subject;

    @BeforeEach
    public void setUp() {
        mockedInputStreamReader = mock(InputStreamReader.class);
        subject = new RequestReader();
    }

    @Test
    void requestReaderReturnsAStringFromAInputStreamReader() throws IOException {
        int request = 71;
        int streamHasNotEnded = -1;

        when(mockedInputStreamReader.read()).thenReturn(request).thenReturn(streamHasNotEnded);
        when(mockedInputStreamReader.ready()).thenReturn(true);

        String result = subject.read(mockedInputStreamReader);

        assertEquals("G", result);
    }
}