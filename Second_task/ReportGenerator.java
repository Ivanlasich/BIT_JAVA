package Second_task;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

interface ReportGenerator<T> {
    Report generate(List<T> entities) throws NoSuchFieldException, IllegalAccessException, IOException;
}



