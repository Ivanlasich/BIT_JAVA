package First_task;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello word");
        Customer example;
        example = new Customer("John", "Goodman");
        System.out.println(example.withdrawFromCurrentAccount(22));
    }
}
