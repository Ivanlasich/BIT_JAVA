package First_task;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestCustomer {
    @Test
    public void customerAlreadyHasAccount() {
        Customer person = new Customer("John", "Goodman");
        person.openAccount(0);
        assertTrue( person.openAccount(0) == false);
    }

    @Test
    public void customerNoActiveAccount() {
        Customer person = new Customer("John", "Goodman");
        assertTrue( person.closeAccount() == false);
    }

    @Test
    public void customerFullName() {
        Customer person = new Customer("John", "Goodman");
        assertTrue( person.fullName().equals("John Goodman"));
    }

}
