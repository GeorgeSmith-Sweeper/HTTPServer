package com.GeorgesServer.app;

import java.io.IOException;
import java.io.InputStream;

public class RequestReader {

    public String read(InputStream reader) {
        StringBuilder sb = new StringBuilder();
        int aByte;
        try {
            while (((aByte = reader.read()) != -1)) {
                char character = (char) aByte;
                sb.append(character);
                if (reader.available() == 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
