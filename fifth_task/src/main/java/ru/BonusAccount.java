package main.java.ru;

import java.time.LocalDateTime;
import java.util.Collection;


public class BonusAccount implements Account {
    private final long id;

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    private final TransactionManager transactionManager;
    private final Entries entries;
    private double bonus = 0.1;

    public long getId() {
        return id;
    }

    public Entries getEntries() {
        return entries;
    }

    public BonusAccount(long id, TransactionManager transactionManager, double bonus) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
        this.bonus = bonus;
    }

    public BonusAccount(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
        this.bonus = 0.01;
    }

    public BonusAccount(long id) {
        this.id = id;
        this.transactionManager = null;
        this.entries = null;
        this.bonus = 0.0;
    }

    /**
     * Withdraws money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, Account beneficiary) {
        double balance = this.balanceOn(LocalDateTime.MIN);
        if (amount > 0 && (balance - amount) > 0) {
            Transaction transaction = this.transactionManager.createTransaction(-amount, this, beneficiary);
            this.transactionManager.executeTransaction(transaction);
            return true;
        }

        return false;
    }

    /**
     * Withdraws cash money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdrawCash(double amount) {
        double balance = this.balanceOn(LocalDateTime.MIN);
        if (amount > 0 && (balance - amount) > 0) {
            Account plug = new DebitCard(-1, null);
            Transaction transaction = this.transactionManager.createTransaction(-amount, this, plug);
            this.transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    /**
     * Adds cash money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean addCash(double amount) {
        if (amount > 0) {
            Account plug = new DebitCard(-1, null);
            Transaction transaction = this.transactionManager.createTransaction(amount, this, plug);
            this.transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    /**
     * Adds money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean add(double amount, Account beneficiary) {
        if (amount > 0) {
            Transaction transaction = this.transactionManager.createTransaction(amount, this, beneficiary);
            this.transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    public Collection<Entry> history(LocalDateTime from, LocalDateTime to) {
        return this.entries.betweenDates(from, to);
    }

    /**
     * Calculates balance on the accounting entries basis
     *
     * @param date
     * @return balance
     */
    public double balanceOn(LocalDateTime date) {
        Collection<Entry> list = this.entries.from(date);
        double sum = 0;
        for (Entry i : list) {
            sum += i.getAmount();
        }
        return sum;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        Entry entry = this.entries.last();
        this.transactionManager.rollbackTransaction(entry.getTransaction());
    }

    public void addEntry(Entry entry) {
        this.entries.addEntry(entry);
    }

    public double getBonus() {
        double answer = 0.0;
        Entries entries = ((BonusAccount) this).getEntries();
        for (Entry entry : entries.betweenDates(LocalDateTime.MIN, LocalDateTime.MAX)) {
            if (entry.getAmount() < 0 && entry.getTransaction().getBeneficiary().getId() != -1) {
                answer += entry.getAmount();
            }
        }
        return -answer * bonus;
    }
}
