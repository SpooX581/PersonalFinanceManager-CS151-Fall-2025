import java.util.ArrayList;
public class ReportGenerator {
    private String fullName;
    private String accountNumber;
    private double totalMonthSpent;
    private double totalMonthDeposit;
    private String totalMonthTransactions;
    private double totalMonthInterest;
    private double totalMonthBalance;
    private double totalMonthCashBack;
    private double totalMonthFees;
    
    // no arg default constructor
    public ReportGenerator() {
        this.fullName = "";
        this.accountNumber = "";
        this.totalMonthSpent = 0.0;
        this.totalMonthDeposit = 0.0;
        this.totalMonthTransactions = "";
        this.totalMonthInterest = 0.0;
        this.totalMonthBalance = 0.0;
        this.totalMonthCashBack = 0.0;
        this.totalMonthFees = 0.0;
    }

    // arg constructor
    public ReportGenerator(String fullName, String accountNumber,double totalMonthSpent, double totalMonthDeposit, String totalMonthTransactions,double totalMonthInterest,double totalMonthBalance, double totalMonthCashBack,double totalMonthFees) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.totalMonthSpent = totalMonthSpent;
        this.totalMonthDeposit = totalMonthDeposit;
        this.totalMonthTransactions = totalMonthTransactions;
        this.totalMonthInterest = totalMonthInterest;
        this.totalMonthBalance = totalMonthBalance;
        this.totalMonthCashBack = totalMonthCashBack;
        this.totalMonthFees = totalMonthFees;
    }

    // getters
     public String getFullName() {
        return fullName;
    }

     public String getAccountNumber() {
        return accountNumber;
    }

     public double getTotalMonthSpent() {
        return totalMonthSpent;
    }

    public double getTotalMonthDeposit() {
        return totalMonthDeposit;
    }

    public String getTransactions() {
        return totalMonthTransactions;
    }

    public double getTotalMonthInterest() {
        return totalMonthInterest;
    }

    public double getTotalMonthBalance() {
        return totalMonthBalance;
    }

    public double getTotalMonthCashBack() {
        return totalMonthCashBack;
    }
    
    public double getTotalMonthFees() {
        return totalMonthFees;
    }

    // methods for calculating the totals
    public void calculateTotalMonthSpent(ArrayList<Double> spentAmount) {
        double calculatedTotalMonthSpent = 0.0;
        for (double amount: spentAmount) {
            calculatedTotalMonthSpent += amount;
        }
        this.totalMonthSpent = calculatedTotalMonthSpent;
    }

    public void calculateTotalMonthDeposit(ArrayList<Double> depositAmount) {
        double calculatedTotalMonthDeposit = 0.0;
        for (double amount: depositAmount) {
            calculatedTotalMonthDeposit += amount;
        }
        this.totalMonthDeposit = calculatedTotalMonthDeposit;
    }

    public void calculateTotalMonthTransactions(ArrayList<String> totalTransactions) {
        String monthTransactions = "";
        for (String transactions: totalTransactions) {
            monthTransactions += transactions + "\n";
        }
        this.totalMonthTransactions = monthTransactions;
    }

    public void calculateTotalMonthInterest(ArrayList<Double> interestAmount) {
        double calculatedTotalMonthInterest = 0.0;
        for (double amount: interestAmount) {
            calculatedTotalMonthInterest += amount;
        }
        this.totalMonthInterest = calculatedTotalMonthInterest;
    }

    public void calculateTotalMonthBalance(ArrayList<Double> balanceAmount) {
        double calculatedTotalMonthBalance = 0.0;
        for (double amount: balanceAmount) {
            calculatedTotalMonthBalance += amount;
        }
        this.totalMonthBalance = calculatedTotalMonthBalance;
    }

    public void calculateTotalMonthCashBack(ArrayList<Double> cashBackAmount) {
        double calculatedTotalMonthCashBack = 0.0;
        for (double amount: cashBackAmount) {
            calculatedTotalMonthCashBack += amount;
        }
        this.totalMonthCashBack = calculatedTotalMonthCashBack;
    }

    public void calculateTotalMonthFees(ArrayList<Double> feeAmount) {
        double calculatedTotalMonthFees = 0.0;
        for (double amount: feeAmount) {
            calculatedTotalMonthFees += amount;
        }
        this.totalMonthFees = calculatedTotalMonthFees;
    }

     @Override
    public String toString() {
        return "Full Name: " + fullName + "\n" +
        "Account number: " + accountNumber + "\n" +
        "Total amount spent for this month: $"+String.format("%.2f",totalMonthSpent) + "\n" +
        "Total amount deposited for this month: $"+String.format("%.2f",totalMonthDeposit) + "\n" +
        "Transactions made for this month: " + "\n" + totalMonthTransactions + "\n" +
        "Total amount of interest this: month: $"+String.format("%.2f",totalMonthInterest) + "\n" +
        "Total balance this month: $"+String.format("%.2f",totalMonthBalance) + "\n" +
        "Total cashback this month: $"+String.format("%.2f",totalMonthCashBack) + "\n" +
        "Total fees this month: $"+String.format("%.2f",totalMonthFees) + "\n";
    }
}