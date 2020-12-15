package main.java.reports;

import java.io.OutputStream;
import java.util.List;

interface ReportGenerator<T> {
    Report generate(List<? extends T> entities) throws IllegalAccessException;
}
