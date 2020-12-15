package main.java.store;
import main.java.ru.Account;


public class AccountKeyExtractor implements KeyExtractor<Integer, Account> {

    @Override
    public Integer extract(Account entity) {
        return entity.hashCode();
    }
}
