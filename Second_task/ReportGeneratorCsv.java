package Second_task;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ReportGeneratorCsv<T> implements ReportGenerator {
    String file;
    String customField="";

    public ReportGeneratorCsv(String file, String customField) {
        this.file = file;
        this.customField = customField;
    }
    public ReportGeneratorCsv(String file) {
        this.file = file;
    }

    @Override
    public Report generate(List entities) throws NoSuchFieldException, IllegalAccessException, IOException {
        FileOutputStream fis = new FileOutputStream(file);
        ReportCsv report = new ReportCsv(entities);
        if(customField.isEmpty()){
            report.setOriginalField();
        }
        else {
            report.setFields(customField);
        }
        report.writeTo(fis);
        return report;
    }
}
