import java.util.ArrayList;
import java.util.List;

public class BankAccount implements ProfileInfoInterface {
    private double balance;
    private String accountNum;
    private String accountPin;

    // New: holds transactions that we load from txt
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(double balance, String accountNum, String accountPin) {
        this.balance = balance;
        this.accountNum = accountNum;
        this.accountPin = accountPin;
    }

    public double getMoneyValue() {
        return balance;
    }

    @Override
    public String getIdNumber() { 
        return accountNum;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    } // NEW

    public void addTransaction(Transaction t) {
        transactions.add(t);
    } // NEW

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            System.out.println("Transaction completed.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount deposited has to be positive.");
        } else {
            balance = balance + amount;
            System.out.println("Amount accepted.");
        }
    }

    public void transfer(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Transfer complete.");
            } else {
                System.out.println("Transfer declined. Insufficient funds.");
            }
        } else {
            System.out.println("Amount transferred has to be positive.");
        }
    }

    public void request(double amount) {
        if (amount > 0) {
            System.out.println("Request received.");
        } else {
            System.out.println("Amount requested has to be positive.");
        }
    }

    public void accountMenu() {
        System.out.println();
        System.out.println("Continue managing bank account");
        System.out.println("1. withdraw");
        System.out.println("2. deposit");
        System.out.println("3. transfer");
        System.out.println("4. request");
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNum + "\n" +
               "Account PIN: " + accountPin + "\n" +
               "Balance: $" + String.format("%.2f", balance) + "\n" +
               "Number of Transactions: " + transactions.size() + "\n";
    }
}
