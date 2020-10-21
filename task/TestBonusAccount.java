package task;


import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestBonusAccount {
    @Test
    public void testWithdrawBonusAccount(){
        TransactionManager transactionManager = new TransactionManager();
        double bonusPercentage = 0.25;
        Account account1 = new BonusAccount(1, transactionManager, bonusPercentage);
        Account account2 = new BonusAccount(2, transactionManager, bonusPercentage);
        account1.addCash(100);
        account1.addCash(50);
        account2.addCash(25);
        account1.withdraw(20, account2);
        assertTrue (account1.getEntries().getSumEntries() == 130);
    }
    @Test
    public void testGetBonusScores(){
        TransactionManager transactionManager = new TransactionManager();
        double bonusPercentage = 0.25;
        Account account1 = new BonusAccount(1, transactionManager, bonusPercentage);
        Account account2 = new BonusAccount(2, transactionManager, bonusPercentage);
        account1.addCash(100);
        account1.addCash(50);
        account2.addCash(50);
        account1.withdraw(100, account2);
        assertTrue (((BonusAccount)account1).getBonusScores()== 25);
    }
}
