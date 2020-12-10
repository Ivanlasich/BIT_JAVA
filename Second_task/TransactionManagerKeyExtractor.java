package Second_task;

public class TransactionManagerKeyExtractor implements KeyExtractor{
    @Override
    public Object extract(Object entity) {
        return ((TransactionManager) entity).getId();
    }
}


