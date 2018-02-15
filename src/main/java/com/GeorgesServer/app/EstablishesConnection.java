package com.GeorgesServer.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class EstablishesConnection {

    private SocketFactory socketFactory;

    public EstablishesConnection (SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public Connections connect(int port) {
//        socketMaker.makeSockets(port);
//        streamMaker.makeStreams(socketMaker.getClientSocket());
//        streamConverters.makeConverters(steamMakers.getOutPutStream(), streamMakers.getInputStream());
//        makeBufferedReader.makeReader(streamConverters.getInputStreamReader());
//


        ServerSocket serverSocket = socketFactory.createSocket(port);
        Socket clientSocket = socketFactory.createClientSocket(serverSocket);
        InputStream inputStream = socketFactory.createInputStream(clientSocket);
        OutputStream outputStream = socketFactory.createOutPutStream(clientSocket);
        OutputStreamWriter outputStreamWriter = socketFactory.createOutputStreamWriter(outputStream);
        InputStreamReader inputStreamReader = socketFactory.createInputStreamReader(inputStream);
        BufferedReader bufferedReader = socketFactory.createBufferedReader(inputStreamReader);

        return new Connections(bufferedReader, outputStreamWriter);
    }
}
