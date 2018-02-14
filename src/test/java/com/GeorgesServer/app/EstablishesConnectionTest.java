package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EstablishesConnectionTest {
    private EstablishesConnection subject;
    private SocketFactory mockedSocketFactory;
    private ServerSocket mockedServerSocket;
    private InputStream mockedInputStream;
    private InputStreamReader mockedInputStreamReader;
    private BufferedReader mockedBufferedReader;
    private OutputStream mockedOutputStream;
    private OutputStreamWriter mockedOutputStreamWriter;
    private Socket mockedClientSocket;

    @BeforeEach
    void setUp () {
        mockedSocketFactory = mock(SocketFactory.class);
        mockedServerSocket = mock(ServerSocket.class);
        mockedClientSocket = mock(Socket.class);
        mockedInputStream = mock(InputStream.class);
        mockedInputStreamReader = mock(InputStreamReader.class);
        mockedOutputStream = mock(OutputStream.class);
        mockedOutputStreamWriter = mock(OutputStreamWriter.class);
        mockedBufferedReader = mock(BufferedReader.class);
        subject = new EstablishesConnection(mockedSocketFactory);
    }

    @Test
    void establishesConnectionCreatesAnInputAndOutputConnection() {
        int validPortNumber = 6000;
        when(mockedSocketFactory.createSocket(validPortNumber)).thenReturn(mockedServerSocket);
        when(mockedSocketFactory.createClientSocket(mockedServerSocket)).thenReturn(mockedClientSocket);
        when(mockedSocketFactory.createInputStream(mockedClientSocket)).thenReturn(mockedInputStream);
        when(mockedSocketFactory.createInputStreamReader(mockedInputStream)).thenReturn(mockedInputStreamReader);
        when(mockedSocketFactory.createBufferedReader(mockedInputStreamReader)).thenReturn(mockedBufferedReader);
        when(mockedSocketFactory.createOutPutStream(mockedClientSocket)).thenReturn(mockedOutputStream);
        when(mockedSocketFactory.createOutputStreamWriter(mockedOutputStream)).thenReturn(mockedOutputStreamWriter);

        Connections result = subject.connect(validPortNumber);

        assertTrue(result.getIn() instanceof BufferedReader);
        assertTrue(result.getOut() instanceof OutputStreamWriter);
    }
}
