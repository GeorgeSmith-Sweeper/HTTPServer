package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    public String read(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while (true) {
                if ((line = reader.readLine()) != null) {
                    sb.append(line);
                } else {
                    break;
                }
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
