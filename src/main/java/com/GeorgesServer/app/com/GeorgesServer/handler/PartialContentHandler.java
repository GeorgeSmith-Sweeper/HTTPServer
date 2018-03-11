package com.GeorgesServer.app.com.GeorgesServer.handler;

import com.GeorgesServer.app.com.GeorgesServer.request.ClientRequest;
import com.GeorgesServer.app.com.GeorgesServer.response.HttpResponseBuilder;
import com.GeorgesServer.app.com.GeorgesServer.response.ServerResponse;

public class PartialContentHandler implements IHandler{

    private HttpResponseBuilder responseBuilder;
    private ServerResponse serverResponse;

    public PartialContentHandler(String publicFolderPath) {
        responseBuilder = new HttpResponseBuilder();
    }

    @Override
    public ServerResponse handle(ClientRequest clientRequest) {
        responseBuilder.buildHttpVersion();
        responseBuilder.build206Status();

//        ArrayList<String> headers = clientRequest.getHeaders();
//        String rangeHeader = "";
//        for (String header : headers) {
//            if (header.contains("Range")) {
//                rangeHeader = header;
//            }
//        }

        // try splitting the range by "=" and then by "-"
        // alternatively try to find the "-" and the get char on either side
        // the range values will be fed into the file reader as the substring search points
        // the reading of the file must happen in the handler for the correct values to be built by the response builder.


//        String[] range = rangeHeader.split("");
//        System.out.println(range[0]);
//        System.out.println(range[1]);
        responseBuilder.buildContentRangeHeader();
        return responseBuilder.getResponse();
    }
}
