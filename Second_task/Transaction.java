package Second_task;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class Transaction implements Comparable<Transaction>{
    private final long id;
    private final double amount;
    private final Account originator;
    private final Account beneficiary;
    private final boolean executed;
    private final boolean rolledBack;

    public Account getBeneficiary() {
        return beneficiary;
    }

    public long getId() {
        return id;
    }

    public Transaction(long id, double amount, Account originator, Account beneficiary, boolean executed, boolean rolledBack) {
        this.id = id;
        this.amount = amount;
        this.originator = originator;
        this.beneficiary = beneficiary;
        this.executed = false;
        this.rolledBack = false;
    }

    /**
     * Adding entries to both accounts
     * @throws IllegalStateException when was already executed
     */
    public Transaction execute() {
        // write your code here
        if (this.executed == true){
            throw new IllegalStateException();
        }
        Entry entryOriginator = new Entry(this.originator, this, this.amount, LocalDateTime.now());
        Entry entryBeneficiary = new Entry(this.beneficiary, this, -this.amount, LocalDateTime.now());
        originator.addEntry(entryOriginator);
        beneficiary.addEntry(entryBeneficiary);
        return new Transaction(this.id, amount, originator, beneficiary, true, rolledBack);
    }

    /**
     * Removes all entries of current transaction from originator and beneficiary
     * @throws IllegalStateException when was already rolled back
     */
    public Transaction rollback() {
        // write your code here
        if (this.rolledBack == true){
            throw new IllegalStateException();
        }
        Entry entryOriginator = new Entry(this.originator, this, -this.amount, LocalDateTime.now());
        Entry entryBeneficiary = new Entry(this.beneficiary, this, this.amount, LocalDateTime.now());
        originator.addEntry(entryOriginator);
        beneficiary.addEntry(entryBeneficiary);
        return new Transaction(this.id, amount, originator, beneficiary, executed, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public int compareTo(Transaction o) {
        if (this.originator.getId() > o.originator.getId()){
            return 1;
        }
        if (this.originator.getId() == o.originator.getId()){
            return (int)(this.getId() - o.getId());
        }
        return -1;
    }
}