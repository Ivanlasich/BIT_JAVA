package First_task;
import org.junit.Test;
public class TestAccount {
    @Test
    public void withdrawNoActiveAccount() {
        Customer person = new Customer("John", "Goodman");
        assert person.withdrawFromCurrentAccount(10) == false;
    }

    @Test
    public void addMoneyNoActiveAccount() {
        Customer person = new Customer("John", "Goodman");
        assert person.addMoneyToCurrentAccount(10) == false;
    }

    @Test
    public void accountWithdrawNegativeAmount() {
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(-23) == false;
    }

    @Test
    public void accountWithdrawZeroAmount() {
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(0) == false;
    }

    @Test
    public void accountWithdrawNegativeDiff() {
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(23) == false;
    }

    @Test
    public void accountAddNegativeAmount() {
        Account acc = new Account(0);
        assert acc.add(-11.23) == false;
    }

    @Test
    public void accountAddZeroAmount() {
        Account acc = new Account(0);
        assert acc.add(0) == false;
    }

}
