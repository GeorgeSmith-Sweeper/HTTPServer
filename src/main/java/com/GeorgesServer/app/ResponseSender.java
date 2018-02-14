package com.GeorgesServer.app;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ResponseSender {
    public void send (String response, OutputStreamWriter out) {
        try {
            out.write(response);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
