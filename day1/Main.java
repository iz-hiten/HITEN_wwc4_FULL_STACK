public class Main {
    public static void main(String[] args) {

        BankAccount acc1 = new BankAccount("BA101", "Hiten Mehta", 5000);
        acc1.deposit(2000);
        acc1.withdraw(1500);
        acc1.printDetails();

        SavingsAccount sav1 = new SavingsAccount("SA201", "Rohit Sharma", 8000, 5);
        sav1.deposit(3000);
        sav1.withdraw(12000);
        sav1.withdraw(2000);
        sav1.applyInterest();
        sav1.printDetails();
    }
}
