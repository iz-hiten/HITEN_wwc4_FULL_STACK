
class BankAccount {
    String owner;
    double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough funds");
        }
        balance -= amount;
    }

    public String toString() {
        return owner + " - " + balance;
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
public class bankingSystem {
    public static void main(String[] args) {
        BankAccount alice = new BankAccount("Alice", 500);
        BankAccount bob = new BankAccount("Bob", 200);

        System.out.println(alice);
        System.out.println(bob);

        alice.deposit(100);
        System.out.println("After deposit: " + alice);

        try {
            alice.withdraw(600);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            alice.withdraw(300);
            bob.deposit(300);
            System.out.println("Transfer successful");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(alice);
        System.out.println(bob);
    }
}

