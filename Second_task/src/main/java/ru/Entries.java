package main.java.ru;
import java.time.LocalDateTime;
import java.util.*;


public class Entries {
    private final TreeSet<Entry> list;
    private final StatisticsForEntries statisticsForEntries;

    public StatisticsForEntries getStatisticsForEntries() {
        return statisticsForEntries;
    }

    public Entries() {
        this.list = new TreeSet<Entry>();
        this.statisticsForEntries = new StatisticsForEntries();
    }

    public void addEntry(Entry entry) {
        list.add(entry);
        statisticsForEntries.addStatistic(entry);
    }

    public Collection<Entry> from(LocalDateTime date) {
        Entry entry = new Entry(null, null, 0, date);
        return list.tailSet(entry);
    }

    public Collection<Entry> betweenDates(LocalDateTime from, LocalDateTime to) {
        Entry entryFrom = new Entry(null, null, 0, from);
        Entry entryTo = new Entry(null, null, 0, to);
        return list.subSet(entryFrom, true, entryTo, true);
    }

    public Entry last() {
        return list.last();
    }
}
