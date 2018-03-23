package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.IO.ResponseSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResponseSenderTest {

    private ResponseSender subject;
    private OutputStream mockedOutPutStream;

    @BeforeEach
    void setUp () {
        subject = new ResponseSender();
        mockedOutPutStream = mock(OutputStream.class);
    }

    @Test
    void requestIsSentWithStreamWriter() throws IOException {
        String response = "Nothing to see here";

        subject.send(response, mockedOutPutStream);

        verify(mockedOutPutStream).write(response.getBytes());
        verify(mockedOutPutStream).close();
    }
}