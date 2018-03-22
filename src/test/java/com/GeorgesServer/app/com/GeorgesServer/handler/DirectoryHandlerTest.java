package com.GeorgesServer.app.com.GeorgesServer.handler;

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
    private String filesPath = "../HTTPServer/src/test/";

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

    @Test
    void directoryHandlerSendsAText_HtmlContentTypeHeader() {
        String expectedHeaderValue = "text/html";

        subject.handle();
        String result = subject.getHeaders().get("Content-Type");

        assertEquals(expectedHeaderValue, result);
    }

    @Test
    void createLinksBuildsLinksWithTheFilesInTheDirectory() {
        String expectedResult = "<a href=\"/fakeFiles/fake_image.jpeg\">fake_image.jpeg</a><br>" +
                "<a href=\"/fakeFiles/fake_text.txt\">fake_text.txt</a><br>";

        subject.handle();

        String result = subject.createLinks();

        assertEquals(expectedResult, result);;
    }

}