public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            System.out.println("Withdrawal denied! Not enough balance.");
        } else {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn from savings account.");
        }
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        setBalance(getBalance() + interest);
        System.out.println("Interest of " + interest + " applied.");
    }
}
