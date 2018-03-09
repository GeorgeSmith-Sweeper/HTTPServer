package com.GeorgesServer.app.com.GeorgesServer.response;

import java.time.LocalDateTime;
import java.util.Date;

public class ServerResponse {
    private String httpVersion;
    private String statusCode;
    private String statusMsg;
    public String body;

    public String format() {

        byte[] byteArray = new byte[4];
        byteArray[0] = 84;
        byteArray[1] = 104;
        byteArray[2] = 105;

        System.out.println(getHttpVersion() + " " + getStatusCode() + " " + getStatusMsg() + "\r\n" +
                "Date: " + LocalDateTime.now() +"\r\n" +
                "Content-Range: bytes 0-4\r\n" +
                "\n" + byteArray);

        return getHttpVersion() + " " + getStatusCode() + " " + getStatusMsg() + "\r\n" +
                "Date: " + LocalDateTime.now() +"\r\n" +
                "Content-Range: bytes 0-4\r\n" +
                "\n" + byteArray;
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
