package test.java.ru;
import main.java.ru.*;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import static junit.framework.TestCase.assertEquals;


public class TestEntries {
    @Test
    public void testAddEntry() {
        Entries entries = new Entries();
        Entry a = new Entry(null, null, 20, LocalDateTime.now());
        Entry b = new Entry(null, null, 46, LocalDateTime.now());
        entries.addEntry(a);
        entries.addEntry(b);
        Collection<Entry> collection = entries.from(LocalDateTime.MIN);
        double answ = 0;
        for (Entry entry: collection){
            answ += entry.getAmount();
        }
        assertEquals(66.0, answ);
    }

    @Test
    public void testFrom() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(100);
        TimeUnit.SECONDS.sleep(1);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(81);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).withdrawCash(37);

        Collection<Entry> list1 = new TreeSet<Entry>();
        list1.add(new Entry(null, null, 81, from));
        list1.add(new Entry(null, null, -37, from));
        Entries entries = ((DebitCard) account1).getEntries();
        Collection<Entry> list = entries.from(from);


        Iterator<Entry> it1 = list1.iterator();
        Iterator<Entry> it2 = list.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Entry pair1 = it1.next();
            Entry pair2 = it2.next();
            assertEquals(pair1, pair2);
        }
    }

    @Test
    public void testBetweenDates() throws InterruptedException {
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

        Collection<Entry> list1 = new TreeSet<Entry>();
        list1.add(new Entry(null, null, 81, to));
        list1.add(new Entry(null, null, 100, from));

        Entries entries = ((DebitCard) account1).getEntries();
        Collection<Entry> list = entries.betweenDates(from, to);

        Iterator<Entry> it1 = list.iterator();
        Iterator<Entry> it2 = list1.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Entry pair1 = it1.next();
            Entry pair2 = it2.next();
            assertEquals(true, pair1.equals(pair2));
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
        assertEquals(16.0, one.getAmount());
    }
}
