package com.GeorgesServer.app;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Streams {

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;

    public Streams(InputStreamReader inputStreamReader, OutputStreamWriter outputStreamWriter) {
        this.inputStreamReader = inputStreamReader;
        this.outputStreamWriter = outputStreamWriter;
    }

    public InputStreamReader getIn() {
        return this.inputStreamReader;
    }

    public OutputStreamWriter getOut() {
        return this.outputStreamWriter;
    }
}
