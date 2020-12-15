package test.java.ru;

import main.java.ru.*;
import main.java.store.AccountKeyExtractor;
import main.java.store.KeyExtractor;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;


public class TestAnalyticsManager {
    @Test
    public void mostFrequentBeneficiaryOfAccount() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        ((DebitCard) account1).addCash(100);
        ((DebitCard) account2).addCash(1000);
        ((DebitCard) account3).addCash(1000);
        ((DebitCard) account4).addCash(1000);
        ((DebitCard) account1).add(10, account2);
        ((DebitCard) account1).add(20, account2);
        ((DebitCard) account1).add(30, account2);
        ((DebitCard) account1).add(45, account2);
        ((DebitCard) account1).add(10, account3);
        ((DebitCard) account1).add(45, account3);
        ((DebitCard) account1).add(10, account3);
        ((DebitCard) account1).add(10, account4);
        ((DebitCard) account1).add(10, account4);
        Entries entries = ((DebitCard) account1).getEntries();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, ((DebitCard) account1).getTransactionManager().getStatisticsForAccount().getMostFrequent(account1), entries);
        Account answ = analyticsManager.mostFrequentBeneficiaryOfAccount(account1);
        assertEquals(2, ((DebitCard) answ).getId());
    }

    @Test
    public void testAccountAddCashTrue() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(45);
        ((DebitCard) account1).addCash(918);
        ((DebitCard) account1).addCash(98);
        ((DebitCard) account1).addCash(12);
        ((DebitCard) account1).addCash(390);
        ((DebitCard) account1).addCash(915);
        ((DebitCard) account1).addCash(957);
        ((DebitCard) account1).addCash(8);
        ((DebitCard) account1).addCash(128);
        ((DebitCard) account1).addCash(998);
        ((DebitCard) account1).addCash(137);
        ((DebitCard) account1).addCash(76);
        ((DebitCard) account1).addCash(91);
        ((DebitCard) account1).addCash(42);
        ((DebitCard) account1).addCash(908);
        ((DebitCard) account1).addCash(178);
        ((DebitCard) account1).addCash(31);

        Entries entries = ((DebitCard) account1).getEntries();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, ((DebitCard) account1).getTransactionManager().getStatisticsForAccount().getMostFrequent(account1), entries);
        Collection<Transaction> top10 = analyticsManager.topTenExpensivePurchases(account1);
        Collection<Transaction> topAct = new ArrayList<>();
        topAct.add(new Transaction(0, 123, null, null, false, false));
        topAct.add(new Transaction(0, 128, null, null, false, false));
        topAct.add(new Transaction(0, 137, null, null, false, false));
        topAct.add(new Transaction(0, 178, null, null, false, false));
        topAct.add(new Transaction(0, 390, null, null, false, false));
        topAct.add(new Transaction(0, 908, null, null, false, false));
        topAct.add(new Transaction(0, 915, null, null, false, false));
        topAct.add(new Transaction(0, 918, null, null, false, false));
        topAct.add(new Transaction(0, 957, null, null, false, false));
        topAct.add(new Transaction(0, 998, null, null, false, false));
        Collections.reverse((List<Transaction>) topAct);
        assertEquals(top10, topAct);
    }

    @Test
    public void testAccountAddCashFalse() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(45);
        ((DebitCard) account1).addCash(918);
        ((DebitCard) account1).addCash(98);
        ((DebitCard) account1).addCash(12);
        ((DebitCard) account1).addCash(390);
        ((DebitCard) account1).addCash(915);
        ((DebitCard) account1).addCash(957);
        ((DebitCard) account1).addCash(8);
        ((DebitCard) account1).addCash(128);
        ((DebitCard) account1).addCash(998);
        ((DebitCard) account1).addCash(137);
        ((DebitCard) account1).addCash(76);
        ((DebitCard) account1).addCash(91);
        ((DebitCard) account1).addCash(42);
        ((DebitCard) account1).addCash(908);
        ((DebitCard) account1).addCash(178);
        ((DebitCard) account1).addCash(31);
        Entries entries = ((DebitCard) account1).getEntries();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, ((DebitCard) account1).getTransactionManager().getStatisticsForAccount().getMostFrequent(account1), entries);
        Collection<Transaction> top10 = analyticsManager.topTenExpensivePurchases(account1);
        Collection<Transaction> topAct = new ArrayList<>();
        topAct.add(new Transaction(0, 123, null, null, false, false));
        topAct.add(new Transaction(0, 128, null, null, false, false));
        topAct.add(new Transaction(0, 137, null, null, false, false));
        topAct.add(new Transaction(0, 178, null, null, false, false));
        topAct.add(new Transaction(0, 390, null, null, false, false));
        topAct.add(new Transaction(0, 908, null, null, false, false));
        topAct.add(new Transaction(0, 915, null, null, false, false));
        topAct.add(new Transaction(0, 918, null, null, false, false));
        topAct.add(new Transaction(0, 957, null, null, false, false));
        topAct.add(new Transaction(0, 98, null, null, false, false));
        assertNotEquals(top10, topAct);
    }

    @Test
    public void testtestOverallBalanceOfAccounts() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        ((DebitCard) account1).addCash(123);
        ((DebitCard) account1).addCash(45);
        ((DebitCard) account1).addCash(98);
        ((DebitCard) account2).addCash(423);
        ((DebitCard) account2).addCash(152);
        ((DebitCard) account3).addCash(228);
        ((DebitCard) account3).addCash(345);
        ((DebitCard) account3).addCash(198);
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, null, null);
        assertTrue(analyticsManager.overallBalanceOfAccounts(arrayList) == 1612);
    }

    @Test
    public void testUniqueKeysOf() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(2, transactionManager);
        List arrayList = new ArrayList();
        arrayList.add(account3);
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account2);
        arrayList.add(account4);
        arrayList.add(account3);
        arrayList.add(account4);
        KeyExtractor extractor = new AccountKeyExtractor();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, null, null);
        assertEquals(analyticsManager.uniqueKeysOf(arrayList, extractor).size(), 4);
    }

    @Test
    public void testAccountsRangeFrom() {
        Comparator comparator = new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return (int) (o1.getId() - o2.getId());
            }
        };

        TransactionManager transactionManager = new TransactionManager();
        List arrayList = new ArrayList();
        Account account5 = new DebitCard(5, transactionManager);
        arrayList.add(account5);
        Account account3 = new DebitCard(3, transactionManager);
        arrayList.add(account3);
        Account account1 = new DebitCard(1, transactionManager);
        arrayList.add(account1);
        Account account6 = new DebitCard(6, transactionManager);
        arrayList.add(account6);
        Account account4 = new DebitCard(4, transactionManager);
        arrayList.add(account4);
        Account account2 = new DebitCard(2, transactionManager);
        arrayList.add(account2);
        Account account8 = new DebitCard(8, transactionManager);
        arrayList.add(account8);
        Account account7 = new DebitCard(7, transactionManager);
        arrayList.add(account7);
        List answer = new ArrayList();
        answer.add(account6);
        answer.add(account7);
        answer.add(account8);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager, null, null);
        assertTrue(analyticsManager.accountsRangeFrom(arrayList, account6, comparator).equals(answer));
    }

    @Test
    public void testMaxExpenseAmountEntryWithinInterval() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).addCash(1000);
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).withdrawCash(581);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).withdrawCash(18);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account1).withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);
        Account account2 = new DebitCard(1, transactionManager);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account2).addCash(1000);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account2).withdrawCash(421);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account2).withdrawCash(81);
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account2).withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);
        to = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        ((DebitCard) account2).withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);
        List arr = new ArrayList();
        arr.add(account1);
        arr.add(account2);
        AnalyticsManager streamAnalyticsManager = new AnalyticsManager(transactionManager, null, null);
        Optional o = streamAnalyticsManager.maxExpenseAmountEntryWithinInterval(arr, from, to);
        assertTrue(((Entry) o.get()).getAmount() == -581.0);
    }
}
