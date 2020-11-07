package main.java.ru;
import java.util.*;


public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        Map<Account, Integer> mostFrequent = account.getTransactionManager().getStatisticsForAccount().getMostFrequent(account);
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
        List<Transaction> topTen = new ArrayList<Transaction>();
        Entries entries = account.getEntries();
        Set<Entry> tree = entries.getStatisticsForEntries().getSetForAnalitics();
        for (Entry entry : tree) {
            topTen.add(entry.getTransaction());
        }
        return topTen.subList(topTen.size() - 10, topTen.size());
    }
}

