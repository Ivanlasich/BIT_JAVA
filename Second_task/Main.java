package Second_task;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // write your code here

        TransactionManager transactionManager1 = new TransactionManager();
        Account account1 = new Account(1, transactionManager1);
        Account account2 = new Account(2, transactionManager1);
        Account account3 = new Account(3, transactionManager1);
        account1.addCash(123);
        account1.addCash(45);
        account1.addCash(98);

        double ex = account1.getEntries().getSumEntries();
        System.out.println(ex);


    }
}
