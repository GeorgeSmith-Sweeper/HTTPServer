package com.GeorgesServer.app;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseSender {
    public void send (String response, OutputStream out) {
        try {
            out.write(response.getBytes("ISO-8859-1"));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
