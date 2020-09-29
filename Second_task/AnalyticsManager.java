package Second_task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;


public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        // write your code here
        HashMap<Account, Integer> mostFrequent = account.mostFrequent;
        Iterator<Account> iterator =  mostFrequent.keySet().iterator();
        Account answ = new Account(-1);
        int s = 0;
        while(iterator.hasNext()) {
            Account key = iterator.next();
            int value = mostFrequent.get(key);
            if (value > s){
                s = value;
                answ = key;
            }
        }
        return answ;
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        // write your code here
        ArrayList<Transaction> topTen = new ArrayList<Transaction>();
        Entries entries = account.getEntries();
        TreeSet<Entry> tree = entries.getListForAnalitics();
        for (Entry entry : tree) {
            topTen.add(entry.getTransaction());
        }
        return topTen.subList(topTen.size() - 10, topTen.size());
    }
}

