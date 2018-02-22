package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    public String read(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while (true) {
                if (reader.ready() && (line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                } else {
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
