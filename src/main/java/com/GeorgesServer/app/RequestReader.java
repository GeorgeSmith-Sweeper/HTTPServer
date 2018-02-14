package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    public String read(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
