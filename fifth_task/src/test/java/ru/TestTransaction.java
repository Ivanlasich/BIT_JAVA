package test.java.ru;

import main.java.ru.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;


public class TestTransaction {
    @Test
    public void testExecuteCatchException() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(229);
        Entry entry = ((DebitCard) account1).getEntries().last();
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
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(229);
        Entry entry = ((DebitCard) account1).getEntries().last();
        Transaction transaction = entry.getTransaction();
        transaction.rollback();
        assertTrue(account1.balanceOn(LocalDateTime.MIN) == 123);
    }
}


