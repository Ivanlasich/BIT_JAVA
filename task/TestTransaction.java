package task;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestTransaction {
    @Test
    public void testExecuteCatchException() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        account1.addCash(123);
        account1.addCash(229);
        Entry entry = account1.getEntries().last();
        Transaction transaction = entry.getTransaction();
        try {
            transaction.execute();
        } catch (IllegalStateException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }


    @Test
    public void testRollback() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        account1.addCash(123);
        account1.addCash(229);
        Entry entry = account1.getEntries().last();
        Transaction transaction = entry.getTransaction();
        transaction.rollback();
        assertTrue(account1.getEntries().getSumEntries() == 123);
    }
}
