package com.GeorgesServer.app;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParameterHandlerTest {
    private ParameterHandler subject;
    private ClientRequest mockedClientRequest;
    private String publicFolderPath = "../cob_spec/public/";


    @BeforeEach
    private void setUp() {
        mockedClientRequest = mock(ClientRequest.class);
        subject = new ParameterHandler(publicFolderPath, mockedClientRequest);
    }

    @Test
    void getQueriesCanParseIncomingQueries() {
        String firstQuery = "car=jaguar";
        String secondQuery = "cool=cat";
        when(mockedClientRequest.getUrl()).thenReturn("/hello?car=jaguar&cool=cat");

        subject.handle();
        String[] result = subject.getQueries();

        assertEquals(firstQuery, result[0]);
        assertEquals(secondQuery, result[1]);
    }
}