package util;

import java.io.*;

public class CountingOutputStream extends OutputStream {
    private long bytes;

    public void write(int b) throws IOException {
        bytes++;
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "bytes streamed=" + Memory.format(bytes, Memory.BYTES, 1);
    }
}
