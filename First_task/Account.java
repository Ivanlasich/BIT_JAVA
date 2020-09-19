package First_task;

public class Account {
    private final long id;
    private double balance;

    public Account(long id) {
        this.id = id;
    }

    /**
     * Withdraws money from account balance
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (balance - amount) &gt 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount) {
        // write your code here
        if (amount > 0 && (balance - amount) > 0){
            balance = balance - amount;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds money to account balance
     *
     * @param amount amount of money to add on account
     * @return true if amount &gt 0, otherwise returns false
     */
    public boolean add(double amount) {
        // write your code here
        if (amount > 0){
            balance = balance + amount;
            return true;
        }
        else {
            return false;
        }
    }
}
