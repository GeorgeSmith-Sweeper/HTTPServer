package com.GeorgesServer.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestReader {

    public String read(InputStreamReader reader) {
        StringBuilder sb = new StringBuilder();
        int aByte;
        try {
            while (((aByte = reader.read()) != -1) && reader.ready()) {
                char character = (char) aByte;
                sb.append(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
