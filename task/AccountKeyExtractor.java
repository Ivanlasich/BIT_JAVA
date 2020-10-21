package task;

public class AccountKeyExtractor implements KeyExtractor {

    @Override
    public Object extract(Object entity) {
        return ((DebitCard) entity).getId();
    }
}
