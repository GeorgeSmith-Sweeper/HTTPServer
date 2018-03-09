package com.GeorgesServer.app;

import java.io.IOException;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseSender {
    public void send (byte[] response, OutputStream out) {
        byte[] the206 = ("HTTP/1.1" + " " + "206" + " " + "Partial Content" + "\r\n" +
                "Date: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) +"\r\n" +
                "Content-Range: bytes 0-4/76\r\n" +
                "Content-Length: 5\r\n" +
                "Content-Type: text/html\r\n" +
                "\n").getBytes();
        try {
            out.write(the206);
            out.write(response);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
