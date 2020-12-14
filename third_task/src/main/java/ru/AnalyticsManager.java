package main.java.ru;
import main.java.store.KeyExtractor;

import java.time.LocalDateTime;
import java.util.*;


public class AnalyticsManager {
    private final TransactionManager transactionManager;
    private Map<Account, Integer> mostFrequent;
    private  Entries entries;

    public AnalyticsManager(TransactionManager transactionManager, Map<Account, Integer> mostFrequent, Entries entries) {
        this.transactionManager = transactionManager;
        this.mostFrequent = mostFrequent;
        this.entries = entries;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        Iterator<Account> iterator =  mostFrequent.keySet().iterator();
        Account answ = null;
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
        Set<Entry> tree = entries.getStatisticsForEntries().getSetForAnalitics();
        for (Entry entry : tree) {
            topTen.add(entry.getTransaction());
        }
        return topTen.subList(topTen.size() - 10, topTen.size());
    }

    public double overallBalanceOfAccounts(List<? extends Account> accounts) {
        double answer = 0.0;
        for (Object account: accounts){
            answer += ((Account) account).balanceOn(LocalDateTime.MIN);
        }
        return answer;
    }

    public<K extends Integer, V extends Account> Set uniqueKeysOf(List<V> accounts, KeyExtractor<K, Account> extractor) {
        Set<K> answer = new HashSet<>();
        for(Account account : accounts) {
            answer.add(extractor.extract(account));
        }
        return answer;
    }

    public <V extends Account> List<V> accountsRangeFrom(List<? extends V> accounts, V minAccount, Comparator<? super V> comparator) {
        Collections.sort(accounts, comparator);
        int index = Collections.binarySearch(accounts, minAccount, comparator);
        return (List<V>) accounts.subList(index, accounts.size());
    }
}

