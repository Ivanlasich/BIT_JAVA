package Second_task;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class TestEntries {
    @Test
    public void testAddEntry() {
        Entries entries = new Entries();
        Entry a = new Entry(null, null, 20, LocalDateTime.now());
        Entry b = new Entry(null, null, 46, LocalDateTime.now());
        entries.addEntry(a);
        entries.addEntry(b);
        assert (entries.getSumEntries() == 66);
    }

    @Test
    public void testFrom() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        TimeUnit.SECONDS.sleep(1);
        account1.addCash(100);
        TimeUnit.SECONDS.sleep(1);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        account1.addCash(81);
        TimeUnit.SECONDS.sleep(1);
        account1.withdrawCash(37);

        Collection<Entry> list1 = new TreeSet<Entry>();
        list1.add(new Entry(null, null, 81, from));
        list1.add(new Entry(null, null, -37, from));
        Entries entries = account1.getEntries();
        Collection<Entry> list = entries.from(from);


        Iterator<Entry> it1 = list1.iterator();
        Iterator<Entry> it2 = list.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Entry pair1 = it1.next();
            Entry pair2 = it2.next();
            assertTrue(pair1.equals(pair2)== true);
        }
    }

    @Test
    public void testBetweenDates() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        account1.addCash(100);
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);
        account1.addCash(81);
        TimeUnit.SECONDS.sleep(1);
        to = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        account1.withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);

        Collection<Entry> list1 = new TreeSet<Entry>();
        list1.add(new Entry(null, null, 81, to));
        list1.add(new Entry(null, null, 100, from));

        Entries entries = account1.getEntries();
        Collection<Entry> list = entries.betweenDates(from, to);

        Iterator<Entry> it1 = list.iterator();
        Iterator<Entry> it2 = list1.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Entry pair1 = it1.next();
            Entry pair2 = it2.next();
            assertTrue(pair1.equals(pair2)== true);
        }
    }

    @Test
    public void testLast(){
        Entries entries = new Entries();
        Entry a = new Entry(null, null, 20, LocalDateTime.now());
        Entry b = new Entry(null, null, 46, LocalDateTime.now());
        Entry c = new Entry(null, null, 16, LocalDateTime.now());

        entries.addEntry(a);
        entries.addEntry(b);
        entries.addEntry(c);
        Entry one = entries.last();
        assert (one.getAmount()==16);
    }

}
