package main.java.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ReportCsv implements Report {

    private final byte[] data;

    public ReportCsv(byte[] data) {
        this.data = data;
    }

    @Override
    public byte[] asBytes() {
        return data;
    }

    @Override
    public void writeTo(OutputStream os){
        try {
            os.write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
