package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StreamMakerTest {
    private StreamMaker subject;
    private ServerSocket mockedServerSocket;
    private InputStream mockedInputStream;
    private OutputStream mockedOutputStream;
    private Socket mockedClientSocket;

    @BeforeEach
    void setUp () {
        mockedServerSocket = mock(ServerSocket.class);
        mockedClientSocket = mock(Socket.class);
        mockedInputStream = mock(InputStream.class);
        mockedOutputStream = mock(OutputStream.class);
        subject = new StreamMaker(mockedServerSocket);
    }

    @Test
    void establishesConnectionCreatesAnInputAndOutputConnection() throws IOException {
        when(mockedServerSocket.accept()).thenReturn(mockedClientSocket);
        when(mockedClientSocket.getInputStream()).thenReturn(mockedInputStream);
        when(mockedClientSocket.getOutputStream()).thenReturn(mockedOutputStream);

        Streams result = subject.connect();

        assertTrue(result.getIn() instanceof InputStreamReader);
        assertTrue(result.getOut() instanceof OutputStreamWriter);
    }
}
