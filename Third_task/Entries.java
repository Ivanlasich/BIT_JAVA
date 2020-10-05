package Second_task;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDate;


public class Entries {

    private TreeSet<Entry> list;
    private TreeSet<Entry> listForAnalitics;


    public TreeSet<Entry> getListForAnalitics() {
        return listForAnalitics;
    }

    public Entries() {
        this.list = new TreeSet<Entry>();
        this.listForAnalitics = new TreeSet<Entry>(new ComparatorForAnalytics());
    }


    void addEntry(Entry entry) {
        // write your code here
        list.add(entry);
        listForAnalitics.add(entry);

    }

    Collection<Entry> from(LocalDateTime date) {
        // write your code here
        Entry entry = new Entry(null, null, 0, date);
        return list.tailSet(entry);
    }



    Collection<Entry> betweenDates(LocalDateTime from, LocalDateTime to) {
        // write your code here
        Entry entryFrom = new Entry(null, null, 0, from);
        Entry entryTo = new Entry(null, null, 0, to);
        return list.subSet(entryFrom, true, entryTo, true);
    }

    Entry last() {
        // write your code here
        return list.last();
    }

    public double getSumEntries() {
        double sum = 0;
        for (Entry i:list) {
            sum += i.getAmount();
        }
        return sum;
    }

}

