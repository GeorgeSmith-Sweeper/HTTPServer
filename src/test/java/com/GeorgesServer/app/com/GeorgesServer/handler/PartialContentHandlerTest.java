package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartialContentHandlerTest {
    private PartialContentHandler subject;
    private ClientRequest mockClientRequest;
    private HashMap<String, String> headers;

    @BeforeEach
    public void setUp() {
        String publicFolderPath = "../cob_spec/public/";
        headers = new HashMap<>();
        mockClientRequest = mock(ClientRequest.class);
        when(mockClientRequest.getUrl()).thenReturn("/partial_content.txt");

        subject = new PartialContentHandler(publicFolderPath, mockClientRequest);
    }

    @Test
    void getBytePositionsReturnsAFirstAndLastBytePosition() {

        headers.put("Range", " bytes=0-4");
        String expectedFirstBytePosition = "0";
        String expectedLastBytePosition = "4";
        when(mockClientRequest.getHeaders()).thenReturn(headers);
        HashMap<String, String> result = subject.getBytePositions(mockClientRequest);

        assertEquals(expectedFirstBytePosition, result.get("first"));
        assertEquals(expectedLastBytePosition, result.get("last"));
    }

    @Test
    void buildContentRangeHeaderBuildsAValidRange() {
        String expectedRange = " bytes 0-4";
        String result = subject.buildContentRangeHeader("0", "4");

        assertEquals(expectedRange, result);
    }

    @Test
    void buildContentLengthHeaderBuildsAValidLength() {
        String expectedLength = " 5";
        String result = subject.buildContentLengthHeader(5);

        assertEquals(expectedLength, result);
    }

    @Test
    void getFileContentsCanReadTheEntireContentsOfATextFile() {
        String expectedContent = "This is a file that contains text to read part of in order to fulfill a 206.\n";
        String result = subject.getFileContents();

        assertEquals(expectedContent, result);
    }

    @Test
    void handlerBuildsCorrectResponseWhenRangeHasAStartAndEndingPosition() {
        headers.put("Range", " bytes=0-4");
        String status = "HTTP/1.1 206 Partial Content\n";
        String contentRange = "Content-Range: bytes 0-4\n";
        String contentLength = "Content-Length: 5\n";
        String body = "\nThis ";
        String expectedResponse = String.join("", status, contentRange, contentLength, body);
        when(mockClientRequest.getHeaders()).thenReturn(headers);

        subject.handle();
        String result = subject.format();

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerBuildsCorrectResponseWhenRangeDoesntHaveAnEndingPosition() {
        headers.put("Range", " bytes=4- ");
        String status = "HTTP/1.1 206 Partial Content\n";
        String contentRange = "Content-Range: bytes 4-76\n";
        String contentLength = "Content-Length: 73\n";
        String body = "\n is a file that contains text to read part of in order to fulfill a 206.\n";
        String expectedResponse = String.join("", status, contentRange, contentLength, body);

        when(mockClientRequest.getHeaders()).thenReturn(headers);

        subject.handle();
        String result = subject.format();

        assertEquals(expectedResponse, result);
    }

    @Test
    void handlerBuildsCorrectResponseWhenRangeDoesntHaveAStartingPosition() {
        headers.put("Range", " bytes= -6");
        String status = "HTTP/1.1 206 Partial Content\n";
        String contentRange = "Content-Range: bytes 71-76\n";
        String contentLength = "Content-Length: 6\n";
        String body = "\n 206.\n";
        String expectedResponse = String.join("", status, contentRange, contentLength, body);

        when(mockClientRequest.getHeaders()).thenReturn(headers);

        subject.handle();
        String result = subject.format();

        assertEquals(expectedResponse, result);
    }
}