package test.java.ru;
import main.java.ru.Account;
import main.java.ru.Entry;
import main.java.ru.Transaction;
import main.java.ru.TransactionManager;
import org.junit.Test;
import java.time.LocalDateTime;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;


public class TestTransaction {
    @Test
    public void testExecuteCatchException() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        account1.addCash(123);
        account1.addCash(229);
        Entry entry = account1.getEntries().last();
        Transaction transaction = entry.getTransaction();
        try {
            transaction.execute();
        } catch (IllegalStateException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
    @Test
    public void testRollback() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        account1.addCash(123);
        account1.addCash(229);
        Entry entry = account1.getEntries().last();
        Transaction transaction = entry.getTransaction();
        transaction.rollback();
        assertTrue(account1.balanceOn(LocalDateTime.MIN) == 123);
    }
}


