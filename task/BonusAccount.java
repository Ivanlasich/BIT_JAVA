package task;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class BonusAccount implements Account {

    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;
    private HashMap<Account, Integer> mostFrequent;
    private final double bonusPercentage;
    private double bonusScores;

    public double getBonusScores() {
        return bonusScores;
    }

    public long getId() {
        return id;
    }

    public Entries getEntries() {
        return entries;
    }

    public BonusAccount(long id, TransactionManager transactionManager, double bonusPercentage) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.bonusPercentage = bonusPercentage;
        this.bonusScores = 0;
        this.entries = new Entries();
        this.mostFrequent = new HashMap<Account, Integer>();

    }

    public HashMap<Account, Integer> getMostFrequent() {
        return mostFrequent;
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
        // write your code here
        double balance = this.entries.getSumEntries();
        if (amount > 0 && (balance - amount) > 0){
            bonusScores += bonusPercentage * amount;
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
        // write your code here
        double balance = this.entries.getSumEntries();
        if (amount > 0 && (balance - amount) > 0){
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
        // write your code here
        if (amount > 0){
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
        // write your code here
        if (amount > 0){
            Transaction transaction = this.transactionManager.createTransaction(amount, this, beneficiary);
            this.transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    public Collection<Entry> history(LocalDateTime from, LocalDateTime to) {
        // write your code here
        return this.entries.betweenDates(from, to);
    }

    /**
     * Calculates balance on the accounting entries basis
     * @param date
     * @return balance
     */
    public double balanceOn(LocalDateTime date) {
        // write your code here
        Collection<Entry> list = this.entries.from(date);
        double sum = 0;
        for (Entry i:list) {
            sum += i.getAmount();
        }
        return sum;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        // write your code here
        Entry entry = this.entries.last();
        this.transactionManager.rollbackTransaction(entry.getTransaction());

    }

    @Override
    public double accountBalance() {
        double sum = 0;
        TreeSet<Entry> tree = entries.getListForAnalitics();
        for (Entry i:tree) {
            sum += i.getAmount();
        }
        return sum;
    }


    public void addEntry(Entry entry){
        this.entries.addEntry(entry);
    }
}
