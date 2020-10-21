package task;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class TestAnalyticsManager {

    @Test
    public void testMostFrequentBeneficiaryOfAccount(){
        TransactionManager transactionManager = new TransactionManager();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);

        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        account1.addCash(100);
        account2.addCash(1000);
        account3.addCash(1000);
        account4.addCash(1000);

        account1.add(10,account2);
        account1.add(20,account2);
        account1.add(30,account2);
        account1.add(45,account2);

        account1.add(10,account3);
        account1.add(45,account3);
        account1.add(10,account3);

        account1.add(10,account4);
        account1.add(10,account4);
        Account answ = analyticsManager.mostFrequentBeneficiaryOfAccount(account1);
        assertTrue(answ.getId()==2);
    }
    @Test
    public void testAccountAddCashTrue() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        account1.addCash(123);
        account1.addCash(45);
        account1.addCash(918);
        account1.addCash(98);
        account1.addCash(12);
        account1.addCash(390);
        account1.addCash(915);
        account1.addCash(957);
        account1.addCash(8);
        account1.addCash(128);
        account1.addCash(998);
        account1.addCash(137);
        account1.addCash(76);
        account1.addCash(91);
        account1.addCash(42);
        account1.addCash(908);
        account1.addCash(178);
        account1.addCash(31);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);
        Collection<Transaction> top10 = analyticsManager.topTenExpensivePurchases(account1);
        Collection<Transaction> topAct = new ArrayList<Transaction>();
        topAct.add(new Transaction(0, 998, null, null, false, false));
        topAct.add(new Transaction(0, 957, null, null, false, false));
        topAct.add(new Transaction(0, 918, null, null, false, false));
        topAct.add(new Transaction(0, 915, null, null, false, false));
        topAct.add(new Transaction(0, 908, null, null, false, false));
        topAct.add(new Transaction(0, 390, null, null, false, false));
        topAct.add(new Transaction(0, 178, null, null, false, false));
        topAct.add(new Transaction(0, 137, null, null, false, false));
        topAct.add(new Transaction(0, 128, null, null, false, false));
        topAct.add(new Transaction(0, 123, null, null, false, false));
        assertTrue (top10.equals(topAct));
    }
    @Test
    public void testAccountAddCashFalse() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        account1.addCash(123);
        account1.addCash(45);
        account1.addCash(918);
        account1.addCash(98);
        account1.addCash(12);
        account1.addCash(390);
        account1.addCash(915);
        account1.addCash(957);
        account1.addCash(8);
        account1.addCash(128);
        account1.addCash(998);
        account1.addCash(137);
        account1.addCash(76);
        account1.addCash(91);
        account1.addCash(42);
        account1.addCash(908);
        account1.addCash(178);
        account1.addCash(31);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);
        Collection<Transaction> top10 = analyticsManager.topTenExpensivePurchases(account1);
        Collection<Transaction> topAct = new ArrayList<Transaction>();

        topAct.add(new Transaction(0, 99, null, null, false, false));
        topAct.add(new Transaction(0, 957, null, null, false, false));
        topAct.add(new Transaction(0, 918, null, null, false, false));
        topAct.add(new Transaction(0, 915, null, null, false, false));
        topAct.add(new Transaction(0, 908, null, null, false, false));
        topAct.add(new Transaction(0, 390, null, null, false, false));
        topAct.add(new Transaction(0, 178, null, null, false, false));
        topAct.add(new Transaction(0, 137, null, null, false, false));
        topAct.add(new Transaction(0, 128, null, null, false, false));
        topAct.add(new Transaction(0, 123, null, null, false, false));
        assertTrue (!top10.equals(topAct));
    }

    @Test
    public void testOverallBalanceOfAccounts() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);

        account1.addCash(123);
        account1.addCash(45);
        account1.addCash(98);

        account2.addCash(423);
        account2.addCash(152);

        account3.addCash(228);
        account3.addCash(345);
        account3.addCash(198);

        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);

        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);
        assertTrue(analyticsManager.overallBalanceOfAccounts(arrayList)==1612);
    }


    @Test
    public void testStreamAccountsRangeFrom() {
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

        AnalyticsManager streamAnalyticsManager = new AnalyticsManager(transactionManager);

        List list = streamAnalyticsManager.accountsRangeFrom(arrayList, account6, comparator);

        assertTrue(streamAnalyticsManager.accountsRangeFrom(arrayList, account6, comparator).equals(answer));


    }

    @Test
    public void testStreamUniqueKeysOf(){
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);


        List arrayList = new ArrayList();
        arrayList.add(account3);
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account2);
        arrayList.add(account4);
        arrayList.add(account3);
        arrayList.add(account4);


        KeyExtractor extractor = new AccountKeyExtractor();
        AnalyticsManager streamAnalyticsManager = new AnalyticsManager(transactionManager);
        Set storage = new HashSet();
        storage.add(account1);
        storage.add(account2);
        storage.add(account3);
        storage.add(account4);

        assertTrue(streamAnalyticsManager.uniqueKeysOf(arrayList, extractor).equals(storage));
    }

    @Test
    public void testHistory() throws InterruptedException {
        LocalDateTime from, to;
        TimeUnit.SECONDS.sleep(1);
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        from = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        account1.addCash(1000);
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);
        account1.withdrawCash(581);
        TimeUnit.SECONDS.sleep(1);
        account1.withdrawCash(18);
        TimeUnit.SECONDS.sleep(1);
        account1.withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);

        Account account2 = new DebitCard(1, transactionManager);
        TimeUnit.SECONDS.sleep(1);
        account2.addCash(1000);
        TimeUnit.SECONDS.sleep(1);
        account2.withdrawCash(421);
        TimeUnit.SECONDS.sleep(1);
        account2.withdrawCash(81);
        TimeUnit.SECONDS.sleep(1);
        account2.withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);
        to = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        account2.withdrawCash(37);
        TimeUnit.SECONDS.sleep(1);

        List arr = new ArrayList();
        arr.add(account1);
        arr.add(account2);
        AnalyticsManager streamAnalyticsManager = new AnalyticsManager(transactionManager);
        Optional o = streamAnalyticsManager.maxExpenseAmountEntryWithinInterval(arr, from, to);
        assertTrue(((Entry)o.get()).getAmount()==-581.0);


    }

}
