package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class PartialContentHandler implements IHandler{

    private HttpResponseBuilder responseBuilder;
    private ServerResponse serverResponse;
    private String publicFolderPath;

    public PartialContentHandler(String publicFolderPath) {
        this.publicFolderPath = publicFolderPath;
        responseBuilder = new HttpResponseBuilder();
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
        responseBuilder.buildHttpVersion();
        responseBuilder.build206Status();

        // get range values from range header
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
        String start = contentRange[0].trim();
        String end = contentRange[1].trim();

        // read the file and store contents
        String fileWeWant = "/partial_content.txt";
        StringBuilder sb = new StringBuilder();
        String fileContents = "";
        List<String> fileArray;
        try {
            Path file = Paths.get(publicFolderPath + fileWeWant);
            fileArray = Files.readAllLines(file);
            for(String line: fileArray) {
                sb.append(line);
            }
            fileContents = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the content with ranges
        if (start.isEmpty()) {
            int convertedEnd = Integer.parseInt(end.trim());
            String noRangeStart = fileContents.substring(fileContents.length()-convertedEnd);
            responseBuilder.buildContentLengthHeader(noRangeStart.length());
            responseBuilder.buildContentRangeHeader(Integer.toString(fileContents.length()-convertedEnd+1), Integer.toString(fileContents.length()));
            responseBuilder.buildBody(noRangeStart);
        } else if (end.isEmpty()) {
            int convertedStart = Integer.parseInt(start.trim());
            String noRangeEnd = fileContents.substring(convertedStart);
            responseBuilder.buildContentLengthHeader(noRangeEnd.length());
            responseBuilder.buildContentRangeHeader(start, Integer.toString(fileContents.length()));
            responseBuilder.buildBody(noRangeEnd);
        } else {
            int convertedEnd = Integer.parseInt(end.trim());
            int convertedStart = Integer.parseInt(start.trim());
            String rangeStartAndEnd = fileContents.substring(convertedStart, convertedEnd+1);
            responseBuilder.buildContentLengthHeader(rangeStartAndEnd.length());
            responseBuilder.buildContentRangeHeader(start, end);
            responseBuilder.buildBody(rangeStartAndEnd);
        }

        return responseBuilder.getResponse();
    }
}
