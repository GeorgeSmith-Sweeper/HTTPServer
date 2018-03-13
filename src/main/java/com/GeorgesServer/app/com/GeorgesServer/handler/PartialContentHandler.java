package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

public class PartialContentHandler implements IHandler{

    private HttpResponseBuilder responseBuilder;
    private String publicFolderPath;

    public PartialContentHandler(String publicFolderPath) {
        this.publicFolderPath = publicFolderPath;
        responseBuilder = new HttpResponseBuilder();
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
        responseBuilder.buildHttpVersion();
        responseBuilder.build206Status();
        String first = getBytePositions(clientRequest).get("first");
        String last = getBytePositions(clientRequest).get("last");
        String fileContents = getFileContents();
        buildHeaders(first, last, fileContents);
        return responseBuilder.getResponse();
    }

    public HashMap<String,String> getBytePositions(ClientRequest clientRequest) {
        HashMap<String, String> positions = new HashMap<>();
        ArrayList<String> headers = clientRequest.getHeaders();
        String rangeHeader = "";
        for (String header : headers) {
            if (header.contains("Range")) {
                rangeHeader = header;
            }
        }
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
        String fileWeWant = "/partial_content.txt";
        Path file = Paths.get(publicFolderPath + fileWeWant);
        String fileContents = "";
        try {
            fileContents = new String(Files.readAllBytes(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    private void buildHeaders(String first, String last, String fileContents) {
        String partialContent;
        
        if (first.isEmpty()) {
            int convertedLast = Integer.parseInt(last);
            partialContent = fileContents.substring(fileContents.length()-convertedLast);
            responseBuilder.buildContentLengthHeader(partialContent.length());
            responseBuilder.buildContentRangeHeader(Integer.toString(fileContents.length()-convertedLast), Integer.toString(fileContents.length()-1));
            responseBuilder.buildBody(partialContent);
        } else if (last.isEmpty()) {
            int convertedFirst = Integer.parseInt(first);
            partialContent = fileContents.substring(convertedFirst);
            responseBuilder.buildContentLengthHeader(partialContent.length());
            responseBuilder.buildContentRangeHeader(first, Integer.toString(fileContents.length()-1));
            responseBuilder.buildBody(partialContent);
        } else {
            int convertedFirst = Integer.parseInt(first);
            int convertedLast = Integer.parseInt(last);
            partialContent = fileContents.substring(convertedFirst, convertedLast+1);
            responseBuilder.buildContentLengthHeader(partialContent.length());
            responseBuilder.buildContentRangeHeader(first, last);
            responseBuilder.buildBody(partialContent);
        }
    }
}
