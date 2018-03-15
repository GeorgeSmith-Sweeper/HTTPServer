package com.GeorgesServer.app;

import java.io.InputStream;
import java.io.OutputStream;

public class Streams {

    private OutputStream outputStream;
    private InputStream inputStream;

    public Streams(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public InputStream getIn() {
        return this.inputStream;
    }

    public OutputStream getOut() {
        return this.outputStream;
    }
}
