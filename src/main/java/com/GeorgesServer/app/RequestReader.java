package com.GeorgesServer.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestReader {

    public String read(InputStreamReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            while (reader.ready()) {
                char character = ((char) reader.read());
                sb.append(character);
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
