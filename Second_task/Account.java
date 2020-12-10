package Second_task;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

public interface Account {
    public double balanceOn(LocalDateTime date);
    public void addEntry(Entry entry);
    public long getId();
    public HashMap<Account, Integer> getMostFrequent();
    public Entries getEntries();

    boolean addCash(double i);

    boolean add(double i, Account account1);

    boolean withdrawCash(double amount);

    Collection<Entry> history(LocalDateTime from, LocalDateTime to);

    public boolean withdraw(double i, Account account2);

    void rollbackLastTransaction();

    public double accountBalance();
}
