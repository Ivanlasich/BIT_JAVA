package First_task;

import org.junit.Test;

public class TestCustomer {
    @Test
    public void customerAlreadyHasAccount() {
        Customer person = new Customer("John", "Goodman");
        person.openAccount(0);
        assert person.openAccount(0) == false;
    }

    @Test
    public void customerNoActiveAccount() {
        Customer person = new Customer("John", "Goodman");
        assert person.closeAccount() == false;
    }

    @Test
    public void customerFullName() {
        Customer person = new Customer("John", "Goodman");
        assert person.fullName().equals("John Goodman");
    }

}
