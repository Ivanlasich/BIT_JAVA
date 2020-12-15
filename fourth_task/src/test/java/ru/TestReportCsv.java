package test.java.ru;

import main.java.reports.ReportCsv;
import main.java.reports.ReportGeneratorCsv;
import main.java.ru.DebitCard;
import main.java.ru.StatisticsForAccount;
import main.java.ru.TransactionManager;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import static junit.framework.TestCase.assertTrue;


public class TestReportCsv {
    @Test
    public void testReportFile() throws IllegalAccessException, IOException {
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
        Class clazz = account1.getClass();
        Map<String, String> usersName = new HashMap<>();
        usersName.put("id", "ID");
        usersName.put("transactionManager", "MANAGER");
        usersName.put("entries", "ENTRY");
        ReportGeneratorCsv<DebitCard> reporterGeneratorCsv = new ReportGeneratorCsv<DebitCard>(usersName, clazz);
        ReportCsv reportCsv = (ReportCsv) reporterGeneratorCsv.generate(entities);
        String file = "data.csv";
        try {
            FileOutputStream fis = new FileOutputStream(file);
            reportCsv.writeTo(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(index == 0){
                    assertTrue(line.equals("ID, MANAGER, ENTRY"));
                }
                else {
                    assertTrue(line.lastIndexOf("TransactionManager")!=-1);
                    assertTrue(line.lastIndexOf("Entries")!=-1);
                    int ind = line.indexOf(",");
                    assertTrue(line.substring(0, ind).matches("[-+]?\\d+"));
                }
                index+=1;
            }
        }
    }

    @Test
    public void testReportStatistic() throws IllegalAccessException, IOException {
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

        StatisticsForAccount statisticsForAccount = transactionManager1.getStatisticsForAccount();
        List entities = Arrays.asList(statisticsForAccount);
        Class clazz = statisticsForAccount.getClass();
        ReportGeneratorCsv<StatisticsForAccount> reporterGeneratorCsv = new ReportGeneratorCsv<>(clazz);
        ReportCsv reportCsv = (ReportCsv) reporterGeneratorCsv.generate(entities);
        String file = "statistic.csv";
        try {
            FileOutputStream fis = new FileOutputStream(file);
            reportCsv.writeTo(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(index == 0){
                    assertTrue(line.equals("mostFrequent"));
                }
                else {
                    assertTrue(line.lastIndexOf("DebitCard")!=-1);
                }
                index+=1;
            }
        }

    }
}
