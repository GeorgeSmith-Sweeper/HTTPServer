package com.GeorgesServer.app;

import java.io.InputStreamReader;
import java.io.OutputStream;

public class Streams {

    private OutputStream outputStream;
    private InputStreamReader inputStreamReader;

    public Streams(InputStreamReader inputStreamReader, OutputStream outputStream) {
        this.inputStreamReader = inputStreamReader;
        this.outputStream = outputStream;
    }

    public InputStreamReader getIn() {
        return this.inputStreamReader;
    }

    public OutputStream getOut() {
        return this.outputStream;
    }
}
