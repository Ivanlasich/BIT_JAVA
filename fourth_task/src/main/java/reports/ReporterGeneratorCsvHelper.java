package main.java.reports;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ReporterGeneratorCsvHelper {
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        if (clazz == null) {
            return fields;
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isSynthetic()|| Modifier.isStatic( field.getModifiers())){
                continue;
            }
            fields.add(field);
        }
        return fields;
    }

    public static List<String> getFieldsNames(List<Field> fields, Map<String, String> usersName) {
        List<String> names = new ArrayList<>();
        if (fields == null) return names;
        for(Field field : fields) {
            String name = field.getName();
            if (usersName!=null){
                names.add(usersName.get(name));
            }
            else {
                names.add(name);
            }
        }
        return names;
    }
}