package main.java.ru;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;


public class TransactionManager {
    /**
     * Creates and stores transactions
     *
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private TreeSet<Transaction> list;
    private final StatisticsForAccount statisticsForAccount;

    public TransactionManager() {
        id = 0;
        list = new TreeSet<Transaction>();
        statisticsForAccount = new StatisticsForAccount();
    }

    public StatisticsForAccount getStatisticsForAccount() {
        return statisticsForAccount;
    }

    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {
        statisticsForAccount.addStatistic(originator, beneficiary);
        id = COUNTER.getAndIncrement();
        Transaction transaction = new Transaction(this.id, amount, originator, beneficiary, false, false);
        return transaction;
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        Transaction lower = list.higher(new Transaction(-1, 0, account, null, false, false));
        Account plug = new Account() {
            @Override
            public double balanceOn(LocalDateTime date) {
                return 0;
            }

            @Override
            public void addEntry(Entry entry) {

            }

            @Override
            public long getId() {
                return account.getId() + 1;
            }

            @Override
            public Collection<Entry> history(LocalDateTime from, LocalDateTime to) {
                return null;
            }
        };
        Transaction higher = list.lower(new Transaction(-1, 0, plug, null, false, false));
        return list.subSet(lower, true, higher, true);
    }


    public void rollbackTransaction(Transaction transaction) {
        list.add(transaction.rollback());
    }

    public void executeTransaction(Transaction transaction) {
        list.add(transaction.execute());
    }
}

