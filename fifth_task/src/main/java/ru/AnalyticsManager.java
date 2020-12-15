package main.java.ru;

import jdk.dynalink.Operation;
import main.java.store.KeyExtractor;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

import java.util.stream.Collectors;


public class AnalyticsManager {
    private final TransactionManager transactionManager;
    private Map<Account, Integer> mostFrequent;
    private Entries entries;

    public AnalyticsManager(TransactionManager transactionManager, Map<Account, Integer> mostFrequent, Entries entries) {
        this.transactionManager = transactionManager;
        this.mostFrequent = mostFrequent;
        this.entries = entries;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        return mostFrequent.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry<Account, Integer>::getValue))
                .map(s -> s.getKey())
                .orElse(null);
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        return entries.getStatisticsForEntries()
                .getSetForAnalitics()
                .descendingSet()
                .stream()
                .map(Entry::getTransaction)
                .limit(10)
                .collect(toList());
    }

    public double overallBalanceOfAccounts(List<? extends Account> accounts) {
        return accounts.stream()
                .mapToDouble(s -> s.balanceOn(LocalDateTime.MIN))
                .sum();
    }

    public <K extends Integer, V extends Account> Set uniqueKeysOf(List<V> accounts, KeyExtractor<K, Account> extractor) {
        return accounts.stream()
                .map(extractor::extract)
                .collect(toSet());
    }

    public <V extends Account> List<V> accountsRangeFrom(List<? extends V> accounts, V minAccount, Comparator<? super V> comparator) {
        return accounts.stream()
                .sorted(comparator)
                .filter(s -> s.getId() >= minAccount.getId())
                .collect(Collectors.toList());
    }

    public Optional<Entry> maxExpenseAmountEntryWithinInterval(List<? extends Account> accounts, LocalDateTime from, LocalDateTime to) {
        return accounts.stream()
                .map(account -> account.history(from, to))
                .flatMap(Collection::stream)
                .filter(entry -> entry.getAmount() < 0)
                .min((Entry o1, Entry o2) -> (int) (o1.getAmount() - o2.getAmount()));
    }
}

