package task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException, IOException {
        // write your code here

        TransactionManager transactionManager = new TransactionManager();

        AnalyticsManager streamAnalyticsManager = new AnalyticsManager(transactionManager);

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
        System.out.println(streamAnalyticsManager.overallBalanceOfAccounts(arrayList));
    }
}
