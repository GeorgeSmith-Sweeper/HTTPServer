package com.GeorgesServer.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class ServerSocketMakerTest {

    private ServerSocketMaker subject;

    @BeforeEach
    void setUp () {
        subject = new ServerSocketMaker();
    }

    @Test
    void serverSocketListensOnTheSuppliedPort() {
        int port = 5000;

        subject.createServerSocket(port);

        assertTrue(subject.getServerSocket() instanceof ServerSocket);
        assertEquals(subject.getServerSocket().getLocalPort(), port);
    }
}