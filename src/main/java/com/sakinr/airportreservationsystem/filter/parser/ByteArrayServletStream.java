package com.sakinr.airportreservationsystem.filter.parser;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;

public class ByteArrayServletStream extends ServletOutputStream {

    ByteArrayOutputStream baos;

    ByteArrayServletStream(ByteArrayOutputStream baos) {
        this.baos = baos;
    }

    @Override
    public void write(int param) {
        baos.write(param);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
    }

}
