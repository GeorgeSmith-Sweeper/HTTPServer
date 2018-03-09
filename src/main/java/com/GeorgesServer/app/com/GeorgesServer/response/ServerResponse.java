package com.GeorgesServer.app.com.GeorgesServer.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ServerResponse {
    private String httpVersion;
    private String statusCode;
    private String statusMsg;
    public String body;

    public byte[] format() {

        byte[] byteArray = new byte[5];
        byteArray[0] = 84;
        byteArray[1] = 104;
        byteArray[2] = 105;
        byteArray[3] = 115;
        byteArray[4] = 32;


        byte[] response = (getHttpVersion() + " " + getStatusCode() + " " + getStatusMsg() + "\r\n" +
                "Date: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) +"\r\n" +
                "Content-Range: bytes 0-4\r\n" +
                "Content-Length: 4\r\n" +
                "Content-Type: text/http\r\n" +
                "\n").getBytes();

        byte[] destination = new byte[response.length + byteArray.length];
        System.arraycopy(response, 0, destination, 0, response.length);
        System.arraycopy(byteArray, 0, destination, response.length, byteArray.length);



        System.out.println("HTTP/1.1" + " " + "206" + " " + "Partial Content" + "\r\n" +
                "Date: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) +"\r\n" +
                "Content-Range: bytes 0-4\r\n" +
                "Content-Length: 4\r\n" +
                "Content-Type: text/html\r\n" +
                "\n" + byteArray);


//        return getHttpVersion() + " " + getStatusCode() + " " + getStatusMsg() + "\r\n" +
//                "Date: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) +"\r\n" +
//                "Content-Range: bytes 0-4\r\n" +
//                "Content-Length: 4\r\n" +
//                "Content-Type: text/http\r\n" +
//                "\n" + byteArray;
        return byteArray;
    }



    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public String getBody() {
        return body;
    }


}
