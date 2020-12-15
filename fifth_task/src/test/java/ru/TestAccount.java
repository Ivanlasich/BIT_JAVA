package test.java.ru;

import main.java.ru.Account;
import main.java.ru.DebitCard;
import main.java.ru.Entry;
import main.java.ru.TransactionManager;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;


public class TestAccount {
    @Test
    public void testAccountAddCash() {
        TransactionManager transactionManager1 = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager1);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(45);
        ((DebitCard) account1).addCash(98);
        assertEquals(266.0, account1.balanceOn(LocalDateTime.MIN));
    }

    @Test
    public void testAdd() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(45);
        ((DebitCard) account1).addCash(98);
        ((DebitCard) account2).addCash(34);
        ((DebitCard) account2).add(23.0, account1);
        assertEquals(243.0, account1.balanceOn(LocalDateTime.MIN));
        assertEquals(57.0, account2.balanceOn(LocalDateTime.MIN));
    }

    @Test
    public void testWithdraw() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        ((DebitCard) account1).addCash(100);
        ((DebitCard) account1).addCash(50);
        ((DebitCard) account2).addCash(25);
        ((DebitCard) account1).withdraw(20, account2);
        assertEquals(130.0, account1.balanceOn(LocalDateTime.MIN));
    }

    @Test
    public void testWithdrawCash() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        ((DebitCard) account1).addCash(100);
        ((DebitCard) account1).addCash(81);
        ((DebitCard) account1).withdrawCash(37);
        assertEquals(144.0, account1.balanceOn(LocalDateTime.MIN));
    }

    @Test
    public void testHistory() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(100);
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(81);
        TimeUnit.SECONDS.sleep(1);
        to = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);

        Collection<Entry> list = ((DebitCard) account1).history(from, to);
        Collection<Entry> list1 = new TreeSet<Entry>();
        list1.add(new Entry(null, null, 81, to));
        list1.add(new Entry(null, null, 100, from));
        Iterator<Entry> it1 = list.iterator();
        Iterator<Entry> it2 = list1.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Entry pair1 = it1.next();
            Entry pair2 = it2.next();
            assertEquals(true, pair1.equals(pair2));
        }
    }

    @Test
    public void testBalanceOn() throws InterruptedException {
        LocalDateTime from;
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(100);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(81);
        TimeUnit.SECONDS.sleep(1);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(230);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(9);
        assertEquals(239.0, account1.balanceOn(from));
    }

    @Test
    public void testRollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(229);
        ((DebitCard) account1).rollbackLastTransaction();
        assertEquals(123.0, account1.balanceOn(LocalDateTime.MIN));
    }
}



