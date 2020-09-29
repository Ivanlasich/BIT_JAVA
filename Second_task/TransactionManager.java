package Second_task;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager {
    /**
     * Creates and stores transactions
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private TreeSet<Transaction> list;

    public TransactionManager() {
        id = 0;
        list = new TreeSet<Transaction>();
    }


    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {
        // write your code here
        HashMap<Account, Integer> mostFrequent = originator.mostFrequent;

        if (mostFrequent.containsKey(beneficiary)){
            int s = mostFrequent.get(beneficiary);
            s +=1;
            mostFrequent.put(beneficiary, s);
        }
        else{
            mostFrequent.put(beneficiary, 1);
        }

        id = COUNTER.getAndIncrement();
        Transaction transaction = new Transaction(this.id, amount, originator, beneficiary, false, false);
        return transaction;
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        // write your code here
        Transaction lower = list.higher(new Transaction(-1,0,account,null,false,false));
        Transaction higher = list.lower(new Transaction(-1,0, new Account(account.getId()+1, null),null,false,false));

        return list.subSet(lower, true, higher, true);
    }


    public void rollbackTransaction(Transaction transaction) {
        // write your code here
        list.add(transaction.rollback());
    }

    public void executeTransaction(Transaction transaction) {
        // write your code here
        list.add(transaction.execute());
    }
}

