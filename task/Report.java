package task;

import java.io.IOException;
import java.io.OutputStream;

interface Report {
    byte[] asBytes(String string);

    void writeTo(OutputStream os) throws IOException, IllegalAccessException;
}
