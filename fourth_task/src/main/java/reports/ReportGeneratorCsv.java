package main.java.reports;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ReportGeneratorCsv<V> implements ReportGenerator<V>{
    private final Map<String, String> usersName;
    private final Class<V> clazz;

    public ReportGeneratorCsv(Map<String, String> usersName, Class<V> clazz) {
        this.usersName = usersName;
        this.clazz = clazz;
    }

    public ReportGeneratorCsv(Class<V> clazz) {
        this.usersName = null;
        this.clazz = clazz;
    }
    @Override
    public Report generate(List<? extends V> entities){
        List<Field> fields = ReporterGeneratorCsvHelper.getFields(clazz);
        List<String> names = ReporterGeneratorCsvHelper.getFieldsNames(fields, usersName);
        StringBuilder builder = new StringBuilder();
        builder.append(String.join(", ", names));
        builder.append("\n");
        for(V entity : entities) {
            for(Field field : fields) {
                field.setAccessible(true);
                try {
                    builder.append(field.get(entity).toString()+", ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            builder.setLength(builder.length()-2);
            builder.append("\n");
        }
        return new ReportCsv(builder.toString().getBytes());
    }
}
