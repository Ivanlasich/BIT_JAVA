package Second_task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException, IOException {
        // write your code here

        TransactionManager transactionManager1 = new TransactionManager();
        DebitCard account1 = new DebitCard(1, transactionManager1);
        DebitCard account2 = new DebitCard(2, transactionManager1);
        DebitCard account3 = new DebitCard(3, transactionManager1);
        DebitCard account4 = new DebitCard(4, transactionManager1);
        account1.addCash(2391);
        account2.addCash(1000);
        account3.addCash(2000);
        account4.addCash(2000);
        account1.add(234, account2);
        account1.add(234, account2);
        account1.add(234, account2);


        account1.add(123, account3);
        account1.withdraw(333, account2);
        account1.withdraw(123, account3);
        account1.add(123, account4);
        account3.add(123, account1);
        account4.withdraw(333, account1);
        account3.withdraw(123, account2);
        account2.add(123, account1);

        account3.add(123, account1);
        account3.withdraw(333, account1);
        account3.withdraw(123, account2);
        account2.add(123, account1);

        account3.add(123, account1);
        account4.withdraw(333, account1);
        account4.withdraw(123, account2);
        account2.add(123, account1);

        List entities = Arrays.asList(account1, account2, account3, account4);
        ReportGenerator<Account> reportGenerator = new ReportGeneratorCsv<Account>("data.csv", "айди, менеджер, записи, самые популярные аккаунты");
        reportGenerator.generate(entities);
    }
}
