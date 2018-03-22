package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DirectoryHandlerTest {
    private DirectoryHandler subject;
    private ClientRequest mockClientRequest;
    private String filesPath = "/Users/8thlight/HTTPServer/src/test/";

    @BeforeEach
    private void setUp() {
        mockClientRequest = mock(ClientRequest.class);
        when(mockClientRequest.getUrl()).thenReturn("fakeFiles");
        subject = new DirectoryHandler(filesPath, mockClientRequest);
    }

    @Test
    void getFilesCanGetAllFilesInADirectory() {
        subject.handle();

        File[] result = subject.getFiles();

        assertEquals(result.length, 2);
    }

}