package First_task;

import org.junit.Test;

public class Tests {
    @Test
    public void customerAlreadyHasAccount(){
        Customer person = new Customer("John", "Goodman");
        person.openAccount(0);
        assert person.openAccount(0) == false;
    }

    @Test
    public void customerNoActiveAccount(){
        Customer person = new Customer("John", "Goodman");
        assert person.closeAccount() == false;
    }

    @Test
    public void customerFullName(){
        Customer person = new Customer("John", "Goodman");
        assert person.fullName().equals("John Goodman");
    }
    @Test
    public void withdrawNoActiveAccount(){
        Customer person = new Customer("John", "Goodman");
        assert person.withdrawFromCurrentAccount(10) == false;
    }
    @Test
    public void addMoneyNoActiveAccount(){
        Customer person = new Customer("John", "Goodman");
        assert person.addMoneyToCurrentAccount(10) == false;
    }
    @Test
    public void accountWithdrawNegativeAmount(){
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(-23) == false;
    }
    @Test
    public void accountWithdrawZeroAmount(){
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(0) == false;
    }
    @Test
    public void accountWithdrawNegativeDiff(){
        Account acc = new Account(0);
        acc.add(11.23);
        assert acc.withdraw(23) == false;
    }
    @Test
    public void accountAddNegativeAmount(){
        Account acc = new Account(0);
        assert acc.add(-11.23) == false;
    }
    @Test
    public void accountAddZeroAmount(){
        Account acc = new Account(0);
        assert acc.add(0) == false;
    }

}
