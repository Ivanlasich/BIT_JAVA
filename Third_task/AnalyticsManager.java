package Second_task;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        // write your code here
        HashMap<Account, Integer> mostFrequent = account.getMostFrequent();
        Iterator<Account> iterator =  mostFrequent.keySet().iterator();
        Account answ = new DebitCard(-1);
        int s = 0;
        while(iterator.hasNext()) {
            Account key = (DebitCard) iterator.next();
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

    public double overallBalanceOfAccounts(List accounts) {
        double answer = 0;
        for (Object account: accounts){
            answer += ((Account) account).accountBalance();
        }
        return answer;
    }

    public Set uniqueKeysOf(List accounts, KeyExtractor extractor) {
        SimpleEntitiesStorage <Long, Account> simpleEntitiesStorage = new SimpleEntitiesStorage<Long, Account>(extractor);
        simpleEntitiesStorage.saveAll(accounts);
        return simpleEntitiesStorage.uniqueKeysOf();
    }

    public List accountsRangeFrom(List accounts, Account minAccount, Comparator comparator) {
        Collections.sort(accounts, comparator);
        int index = Collections.binarySearch(accounts, minAccount, comparator);
        return accounts.subList(index, accounts.size());
    }

}
