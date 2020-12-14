package main.java.ru;
import java.util.TreeSet;


public class StatisticsForEntries {
    private final TreeSet<Entry> setForAnalise;

    public StatisticsForEntries() {
        this.setForAnalise = new TreeSet<>(new ComparatorForAnalytics());
    }

    public void addStatistic(Entry entry){
        setForAnalise.add(entry);
    }

    public TreeSet<Entry> getSetForAnalitics() {
        return setForAnalise;
    }
}
