package main.java.ru;
import java.util.HashMap;


public class StatisticsForAccount{
    private final HashMap<Account, HashMap<Account, Integer>> mostFrequent;

    public StatisticsForAccount() {
        mostFrequent = new HashMap<>();
    }

    public void addStatistic(Account account1, Account account2){
        if(mostFrequent.containsKey(account1)){
            HashMap<Account, Integer> hashMap = mostFrequent.get(account1);
            if(hashMap.containsKey(account2)){
                int s = hashMap.get(account2);
                s += 1;
                hashMap.put(account2, s);
            }
            else {
                hashMap.put(account2, 1);
            }
        }
        else {
            HashMap<Account, Integer> hashMap = new HashMap<>();
            hashMap.put(account2, 1);
            mostFrequent.put(account1, hashMap);
        }
    }

    public HashMap<Account, Integer> getMostFrequent(Account account) {
        return mostFrequent.get(account);
    }
}
