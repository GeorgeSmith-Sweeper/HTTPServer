package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilesHandlerTest {

    private FilesHandler subject;
    private ClientRequest mockClientRequest;
    String publicFolderPath = "../cob_spec/public/";

    @BeforeEach
    public void setUp() {
        mockClientRequest = mock(ClientRequest.class);
    }

    @Test
    void filesHandlerAddsTheFileContentsToTheBodyOfTheResponse() {
        String expectedBody = "file1 contents";
        when(mockClientRequest.getUrl()).thenReturn("/file1");
        subject = new FilesHandler(publicFolderPath, mockClientRequest);

        subject.handle();
        String result = subject.getBody();

        assertEquals(expectedBody, result);
    }

    @Test
    void filesHandlerAddsTheContentTypeToTheHeaders() {
        String expectedContentType = "image/jpeg";
        when(mockClientRequest.getUrl()).thenReturn("/image.jpeg");
        subject = new FilesHandler(publicFolderPath, mockClientRequest);

        subject.handle();
        String result = subject.getHeaders().get("Content-Type");

        assertEquals(expectedContentType, result);
    }
}