package test.java.ru;

import main.java.ru.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;

public class TestBonusAccount {
    @Test
    public void Withdraw(){
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new BonusAccount(1, transactionManager, 0.05);
        Account account2 = new BonusAccount(2, transactionManager);
        ((BonusAccount) account1).addCash(100);
        ((BonusAccount) account1).addCash(50);
        ((BonusAccount) account2).addCash(25);
        ((BonusAccount) account1).withdraw(20, account2);
        ((BonusAccount) account1).withdrawCash(34);
        ((BonusAccount) account1).withdraw(12, account2);
        assertEquals(((BonusAccount) account1).getBonus(), 1.6);
    }
}
