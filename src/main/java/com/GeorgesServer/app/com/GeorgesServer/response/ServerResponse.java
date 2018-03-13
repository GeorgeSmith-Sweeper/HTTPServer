package com.GeorgesServer.app.com.GeorgesServer.response;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

public class ServerResponse {
    private String httpVersion;
    private String statusCode;
    private String statusMsg;
    private String contentRangeHeader;
    private String contentLengthHeader;
    private String body;

    public String format(ClientRequest clientRequest) {
        if (clientRequest.getUrl().equals("/partial_content.txt")) {
            return getHttpVersion() + " " +
                    getStatusCode() + " " +
                    getStatusMsg() + "\n" +
                    getContentRangeHeader() + "\n" +
                    getContentLengthHeader() + "\n\n" +
                    getBody();
        }
        return getHttpVersion() + " " + getStatusCode() + " " + getStatusMsg() + "\n";
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

    
    public String getContentRangeHeader() {
        return contentRangeHeader;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setContentRangeHeader(String contentRangeHeader) {
        this.contentRangeHeader = contentRangeHeader;
    }

    public void setContentLengthHeader(String contentLengthHeader) {
        this.contentLengthHeader = contentLengthHeader;
    }

    public String getContentLengthHeader() {
        return contentLengthHeader;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
