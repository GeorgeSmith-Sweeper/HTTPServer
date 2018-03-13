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
        String first = getBytePositions(clientRequest).get("first");
        String last = getBytePositions(clientRequest).get("last");
        String fileContents = getFileContents();

        // build responses
        if (first.isEmpty()) {
            int convertedlast = Integer.parseInt(last.trim());
            String noRangefirst = fileContents.substring(fileContents.length()-convertedlast);
            responseBuilder.buildContentLengthHeader(noRangefirst.length());
            responseBuilder.buildContentRangeHeader(Integer.toString(fileContents.length()-convertedlast), Integer.toString(fileContents.length()-1));
            responseBuilder.buildBody(noRangefirst);
        } else if (last.isEmpty()) {
            int convertedfirst = Integer.parseInt(first.trim());
            String noRangelast = fileContents.substring(convertedfirst);
            responseBuilder.buildContentLengthHeader(noRangelast.length());
            responseBuilder.buildContentRangeHeader(first, Integer.toString(fileContents.length()-1));
            responseBuilder.buildBody(noRangelast);
        } else {
            int convertedlast = Integer.parseInt(last.trim());
            int convertedfirst = Integer.parseInt(first.trim());
            String rangefirstAndlast = fileContents.substring(convertedfirst, convertedlast+1);
            responseBuilder.buildContentLengthHeader(rangefirstAndlast.length());
            responseBuilder.buildContentRangeHeader(first, last);
            responseBuilder.buildBody(rangefirstAndlast);
        }
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
}
