package task;


public class TransactionKeyExtractor implements KeyExtractor {
    @Override
    public Object extract(Object entity) {
        return ((Transaction) entity).getId();
    }
}
