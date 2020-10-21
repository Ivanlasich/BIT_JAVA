package task;

public class EntryKeyExtractor implements KeyExtractor {
    @Override
    public Object extract(Object entity) {
        return ((Entry) entity).getTime();
    }
}
