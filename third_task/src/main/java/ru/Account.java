package main.java.ru;

import java.time.LocalDateTime;

public interface Account {
    double balanceOn(LocalDateTime date);
    void addEntry(Entry entry);
    long getId();
}
