package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.StatusCodes;
import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;

public class PartialContentHandler implements IHandler{

    private Path file;
    private ClientRequest request;
    private String publicFolderPath;
    private HashMap<String,String> headers;
    private String status;
    private String partialContent;
    private String body;

    public PartialContentHandler(String publicFolderPath, ClientRequest request) {
        this.file = Paths.get(publicFolderPath + request.getUrl());
        this.request = request;
        this.publicFolderPath = publicFolderPath;
    }

    @Override
    public void handle() {
        String first = getBytePositions(request).get("first");
        String last = getBytePositions(request).get("last");
        String fileContents = getFileContents();
        setStatus();
        setHeaders(first, last, fileContents);
        setBody();
    }

    public void setStatus() {
        this.status = StatusCodes.PARTIAL_CONTENT;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    public void setBody() {
        this.body = partialContent;
    }

    @Override
    public String getBody() {
        return this.body;
    }


    public HashMap<String,String> getBytePositions(ClientRequest clientRequest) {
        HashMap<String, String> positions = new HashMap<>();
        HashMap<String, String> headers = clientRequest.getHeaders();
        String rangeHeader = headers.get("Range");

        int equalsLocation = rangeHeader.indexOf("=");
        String range = rangeHeader.substring(equalsLocation+1, rangeHeader.length());
        String[] contentRange = range.split("-");
        String first = contentRange[0].trim();
        String last = contentRange[1].trim();
        positions.put("first", first);
        positions.put("last", last);
        return positions;
    }

    public String getFileContents() {
        String fileContents = "";
        try {
            fileContents = new String(Files.readAllBytes(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    public String buildContentRangeHeader(String start, String end) {
        return String.format(" bytes %s-%s", start, end);
    }

    public String buildContentLengthHeader(int length) {
        return String.format(" %s", length);
    }

    private void setHeaders(String first, String last, String fileContents) {
        this.headers = new HashMap<>();
        if (first.isEmpty()) {
            int convertedLast = Integer.parseInt(last);
            partialContent = fileContents.substring(fileContents.length()-convertedLast);
            headers.put("Content-Range", buildContentRangeHeader(Integer.toString(fileContents.length()-convertedLast), Integer.toString(fileContents.length()-1)));
            headers.put("Content-Length", buildContentLengthHeader(partialContent.length()));
        } else if (last.isEmpty()) {
            int convertedFirst = Integer.parseInt(first);
            partialContent = fileContents.substring(convertedFirst);
            headers.put("Content-Range", buildContentRangeHeader(first, Integer.toString(fileContents.length()-1)));
            headers.put("Content-Length", buildContentLengthHeader(partialContent.length()));
        } else {
            int convertedFirst = Integer.parseInt(first);
            int convertedLast = Integer.parseInt(last);
            partialContent = fileContents.substring(convertedFirst, convertedLast+1);
            headers.put("Content-Range", buildContentRangeHeader(first, last));
            headers.put("Content-Length", buildContentLengthHeader(partialContent.length()));
        }
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String format() {
        StringBuilder response = new StringBuilder();
        response.append(getStatus()).append("\n");
        for (String key : getHeaders().keySet()) {
            String value = getHeaders().get(key);
            response.append(key).append(":").append(value).append("\n");
        }
        response.append("\n").append(getBody());
        return response.toString();
    }
}
