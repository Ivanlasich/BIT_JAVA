package Second_task;

import java.time.LocalDateTime;
import java.util.Objects;

public class Entry implements Comparable<Entry> {

    private final Account account;
    private final Transaction transaction;
    private final double amount;
    private final LocalDateTime time;

    public double getAmount() {
        return amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public LocalDateTime getTime(){
        return time;
    }

    public Entry(Account account, Transaction transaction, double amount, LocalDateTime time){
        this.account = account;
        this.transaction = transaction;
        this.amount = amount;
        this.time = time;
    }


    @Override
    public int compareTo(Entry o) {
        if(this.time.compareTo(o.getTime()) == 0){
            return 1;
        }
        return this.time.compareTo(o.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Double.compare(entry.amount, amount) == 0;
    }

}