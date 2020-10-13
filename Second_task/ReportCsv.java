package Second_task;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class ReportCsv<T> implements Report {

    public void setFields(String fields) {
        this.fields = fields;
    }

    List<T> entities;
    String fields;

    public ReportCsv(List entities) {
        this.entities = entities;
    }

    @Override
    public byte[] asBytes(String string) {
        return string.getBytes();
    }

    @Override
    public void writeTo(OutputStream os) throws IOException, IllegalAccessException {
        String s = fields;
        os.write(asBytes(s));
        os.write(asBytes("\n"));
        Field[] fields;

        for(T entity : entities) {
            s = "";
            fields = entity.getClass().getDeclaredFields();
            for(Field field: fields){
                field.setAccessible(true);
                field.get(entity).toString();
                s = s + field.get(entity).toString() + ", ";
            }
            os.write(asBytes(s));
            os.write(asBytes("\n"));
        }
    }

    public void setOriginalField() throws IOException {
        Field[] fields;
        String s = "";
        for(T entity : entities) {
            fields = entity.getClass().getDeclaredFields();
            for(Field field: fields){
                s = s + field.getName() + ", ";
            }

            break;
        }
        this.fields = s;
    }

}
