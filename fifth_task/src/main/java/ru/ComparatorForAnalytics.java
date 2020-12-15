package main.java.ru;

import java.util.Comparator;


public class ComparatorForAnalytics implements Comparator<Entry> {

    @Override
    public int compare(Entry o1, Entry o2) {
        return (int) (o1.getAmount() - o2.getAmount());
    }
}
