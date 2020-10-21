package task;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        HashMap<Account, Integer> mostFrequent = account.getMostFrequent();
        Optional o = mostFrequent.entrySet()
                .stream()
                .max(new Comparator<Map.Entry<Account, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Account, Integer> o1, Map.Entry<Account, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                }).map(s -> s.getKey());
        return (DebitCard) o.get();
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {

        ArrayList<Transaction> topTen = (ArrayList) account.getEntries().getListForAnalitics().descendingSet().stream().map(s -> ((Entry)s).getTransaction()).limit(10).collect(Collectors.toList());
        return topTen;
    }

    public double overallBalanceOfAccounts(List accounts) {
        double answer = (double) accounts.stream().map(s -> (((Account) s).accountBalance())).reduce(0.0, (acc, x) -> (double) acc + (double) x);

        return answer;
    }

    public Set uniqueKeysOf(List accounts, KeyExtractor extractor) {
        Set set = (Set) accounts.stream().distinct().collect(Collectors.toSet());
        return set;
    }


    public List accountsRangeFrom(List accounts, Account minAccount, Comparator comparator) {
        ArrayList<Account> arr = (ArrayList<Account>) accounts.stream().sorted(comparator).filter(s -> ((Account)s).getId() >= minAccount.getId()).collect(Collectors.toList());
        return arr;
    }

    public Optional<Entry> maxExpenseAmountEntryWithinInterval(List<Account> accounts, LocalDateTime from, LocalDateTime to){
        Optional o = accounts.stream().map(account -> account.history(from, to)).flatMap(entries -> entries.stream()).filter(entry -> entry.getAmount()<0).min(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return (int) (o1.getAmount()-o2.getAmount());
            }
        });

        return o;
    };

}
