package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    public String read(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            if ((line = reader.readLine()) != null) { sb.append(line); }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
