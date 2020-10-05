package Second_task;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // write your code here

        TransactionManager transactionManager1 = new TransactionManager();
        DebitCard account1 = new DebitCard(1, transactionManager1);
        DebitCard account2 = new DebitCard(2, transactionManager1);
        DebitCard account3 = new DebitCard(3, transactionManager1);
        account1.addCash(123);
        account1.addCash(45);
        account1.addCash(98);

        double ex = account1.getEntries().getSumEntries();
        System.out.println(ex);


    }
}
