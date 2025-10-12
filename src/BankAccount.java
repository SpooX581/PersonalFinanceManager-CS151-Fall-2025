public class BankAccount {
    private double balance;
    private String accountNum;
    private String accountPin;

    public BankAccount(double balance, String accountNum, String accountPin) {
        this.balance = balance;
        this.accountNum = accountNum;
        this.accountPin = accountPin;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getAccountPin() {
        return accountPin;
    }

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
                balance = balance - amount;
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

}
