package Second_task;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

public class TestAnalyticsManager {

    @Test
    public void mostFrequentBeneficiaryOfAccount(){
        TransactionManager transactionManager = new TransactionManager();
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);

        Account account1 = new Account(1, transactionManager);
        Account account2 = new Account(2, transactionManager);
        Account account3 = new Account(3, transactionManager);
        Account account4 = new Account(4, transactionManager);
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
    public void AccountAddCashTrue() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
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
        assertTrue (top10.equals(topAct));
    }
    @Test
    public void AccountAddCashFalse() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
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
        assertTrue (!top10.equals(topAct));
    }


}
