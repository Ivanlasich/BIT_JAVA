package test.java.ru;
import main.java.ru.*;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;


public class TestTransactionManager {
    @Test
    public void testFindAllTransactionsByAccount() {
        TransactionManager transactionManager = new TransactionManager();

        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        ((DebitCard) account1).addCash(100);
        ((DebitCard) account2).addCash(1000);
        ((DebitCard) account3).addCash(1000);
        ((DebitCard) account4).addCash(1000);

        ((DebitCard) account1).add(10,account2);
        ((DebitCard) account1).add(20,account2);

        Collection<Transaction> answ = transactionManager.findAllTransactionsByAccount(account1);
        Collection<Transaction> transactions = new TreeSet<Transaction>();
        transactions.add(new Transaction(1,100, account1, null, false, false));
        transactions.add(new Transaction(5,10, account1, null, false, false));
        transactions.add(new Transaction(6,20, account1, null, false, false));

        Iterator<Transaction> it1 = answ.iterator();
        Iterator<Transaction> it2 = transactions.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Transaction pair1 = it1.next();
            Transaction pair2 = it2.next();
            assertTrue(pair1.equals(pair2)== true);
        }
    }

    @Test
    public void testRollbackTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(229);
        Entry entry = ((DebitCard) account1).getEntries().last();
        transactionManager.rollbackTransaction(entry.getTransaction());
        assertTrue(account1.balanceOn(LocalDateTime.MIN) == 123);
    }

    @Test
    public void testExecuteTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(229);
        Entry entry = ((DebitCard) account1).getEntries().last();
        try {
            transactionManager.executeTransaction(entry.getTransaction());
        } catch (IllegalStateException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void testCreateTransaction() {
        TransactionManager transactionManager1 = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager1);
        Account account2 = new DebitCard(2, transactionManager1);
        TransactionManager transactionManager = new TransactionManager();
        Transaction transaction1 = transactionManager.createTransaction(10, account1, account2);
        Transaction transaction2 = transactionManager.createTransaction(10, account1, account2);
        assertEquals( transaction2.getId()-1, transaction1.getId());
    }
}