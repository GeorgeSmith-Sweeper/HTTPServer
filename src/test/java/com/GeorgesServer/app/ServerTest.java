package com.GeorgesServer.app;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ServerTest {

    @InjectMocks
    Server subject;

    @Mock
    EstablishesConnection establishesConnection;

    @Test
    void callingStartOnServerEstablishesAConnectionWithAPortNumber() {
        String portNumber = "5000";
        String folderPath = "";

        Server server = new Server(portNumber, folderPath);

        server.start();


    }
}
