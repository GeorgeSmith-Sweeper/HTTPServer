package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartialContentHandlerTest {
    private PartialContentHandler subject;
    private ClientRequest mockClientRequest;

    @BeforeEach
    public void setUp() {
        String publicFolderPath = "../cob_spec/public/";
        mockClientRequest = mock(ClientRequest.class);
        subject = new PartialContentHandler(publicFolderPath);
    }

    @Test
    void handlerBuildsA206Status() {
        String expectedCode = "206";
        String expectedMsg = "Partial Content";
        String expectedVersion = "HTTP/1.1";
        String expectedBody = "This ";

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes=0-4");
        when(mockClientRequest.getHeaders()).thenReturn(headers);

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedCode, result.getStatusCode());
        assertEquals(expectedMsg, result.getStatusMsg());
        assertEquals(expectedVersion, result.getHttpVersion());
        assertEquals(expectedBody, result.getBody());
    }

    @Test
    void handlerBuildsAContentRangeHeaderWithNoEndRange() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes=4- ");
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        String expectedContentRange = "Content-Range: bytes 4-76";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedContentRange, result.getContentRangeHeader());
    }

    @Test
    void handlerBuildsAContentRangeHeaderWithNoStartRange() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes= -6");
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        String expectedContentRange = "Content-Range: bytes 71-76";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedContentRange, result.getContentRangeHeader());
    }

    @Test
    void handlerBuildsAContentLengthHeader() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes=0-4");
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        String expectedContentRange = "Content-Length: 5";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedContentRange, result.getContentLengthHeader());
    }

    @Test
    void handlerBuildsAContentLengthHeaderWithNoEndRange() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes=4- ");
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        String expectedContentRange = "Content-Length: 72";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedContentRange, result.getContentLengthHeader());
    }

    @Test
    void handlerBuildsAContentLengthHeaderWithNoStartRange() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Range: bytes= -6 ");
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        String expectedContentRange = "Content-Length: 6";

        ServerResponse result = subject.handle(mockClientRequest);

        assertEquals(expectedContentRange, result.getContentLengthHeader());
    }
}