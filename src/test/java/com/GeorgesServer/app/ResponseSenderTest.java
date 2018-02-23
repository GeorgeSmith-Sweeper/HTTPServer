package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResponseSenderTest {

    private ResponseSender subject;
    private OutputStreamWriter mockedOutPutStreamWriter;

    @BeforeEach
    void setUp () {
        subject = new ResponseSender();
        mockedOutPutStreamWriter = mock(OutputStreamWriter.class);
    }

    @Test
    void requestIsSentWithStreamWriter() throws IOException {
        String response = "Nothing to see here";

        subject.send(response, mockedOutPutStreamWriter);

        verify(mockedOutPutStreamWriter).write(response);
        verify(mockedOutPutStreamWriter).close();
    }
}