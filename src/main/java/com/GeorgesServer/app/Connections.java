package com.GeorgesServer.app;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connections {

    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;

    public Connections (BufferedReader bufferedReader, OutputStreamWriter outputStreamWriter) {
        this.bufferedReader = bufferedReader;
        this.outputStreamWriter = outputStreamWriter;
    }

    public BufferedReader getIn() {
        return this.bufferedReader;
    }

    public OutputStreamWriter getOut() {
        return this.outputStreamWriter;
    }
}
