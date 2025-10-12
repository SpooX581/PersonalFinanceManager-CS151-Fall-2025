package src;

import java.util.ArrayList;

public class CreditCard {
    private String creditCardName;
    private String creditCardNumber;
    private double statementBalance;
    private int closingDate;
    private double cashBack;
    private double creditLimit;
    private double interestRate;
    private int dueDate;
    private double minimumPayment;
    private ArrayList<String> purchases = new ArrayList<>(); // storing purchases

    // no arg/default constructor with default values
    public CreditCard() {
      this.creditCardName = "Unknown";  
      this.creditCardNumber = "0000 0000 0000 0000";
      this.statementBalance = 0.0;
      this.closingDate = 0;
      this.cashBack = 0.0;
      this.creditLimit = 0.0;
      this.interestRate = 0.0;
      this.dueDate = 0;
      this.minimumPayment = 0;
    }

    // arg constructor
    public CreditCard (String creditCardName, String creditCardNumber, double statementBalance, int closingDate, double cashBack, double creditLimit, double interestRate, int dueDate, double minimumPayment){
        this.creditCardName = creditCardName;
        this.creditCardNumber = creditCardNumber;
        this.statementBalance = statementBalance;
        this.closingDate = closingDate;
        this.cashBack = cashBack;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.dueDate = dueDate;
        this.minimumPayment = minimumPayment;
    } 

    // getters
    public String getCreditCardName() {
        return creditCardName;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public double getStatementBalance() {
        return statementBalance;
    }
    public int getClosingDate() {
       return closingDate;
    }
    public double getCashBack(){
        return cashBack;
    }
    public double getCreditLimit(){
        return creditLimit;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public int getDueDate() {
        return dueDate;
    }
    public double getMinimumPayment(){
        return minimumPayment;
    }

    // setters
    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setStatementBalance(double statementBalance) {
        this.statementBalance = statementBalance;
    }
   

    // printing & calculating methods
    public void seeCreditCardName() {
        System.out.println("Your credit card: " + creditCardName);
    } 

     public void seeCreditCardNumber() {
        System.out.println("Your credit card number: " + creditCardNumber);
    } 

    public void seeStatementBalance() {
        System.out.println("Checking credit card balance: $" +  String.format("%.2f", statementBalance));
    } 
    
    public void makingAPurchase (String purchase, double amount) {
        if (amount > 0) {
            statementBalance += amount;
            purchases.add(purchase + ": $" + String.format("%.2f", amount)); // adding purchase to ArrayList purchases
            // cashback from purchase
            double cashBackRate = 0.03; // 3% cashback
            double cashBackEarned = amount * cashBackRate; 
            cashBack += cashBackEarned; 
            System.out.println("You earned $" + String.format("%.2f", cashBackEarned) + " cashback on this purchase.");
       } else {
            throw new IllegalArgumentException("Invalid purchase. Please try again.");
       }
    }

    public void creditCardPayment (double paymentAmount) {
            if (paymentAmount > 0) {
            statementBalance -= paymentAmount;
            System.out.println("Successful payment of $" + String.format("%.2f", paymentAmount));
            System.out.println("Your new statement balance is: $" + String.format("%.2f", statementBalance));
        } else {
            throw new IllegalArgumentException("Invalid payment amount. Please try again.");
       }
    }

    public void seePurchases() {
        System.out.println("Your Purchases this Month:");
        if (purchases.isEmpty()){
            System.out.println("No purchases currently exist. You have not made a purchase yet.");
        } else {
            for (String purchaseList : purchases) { 
            System.out.println(purchaseList);
        }
        }
    }

    public void seeClosingDate(){
        closingDate = 20; // always on the 20th day of the month 
        System.out.println("Your closing date is the " + closingDate + "th day of every month.");
    }

    public void seeCashBack() {
        System.out.println("Total cashback this month: $" + String.format("%.2f", cashBack));
    }

    public void seeCreditLimit() {
        creditLimit = 5000.00; // set default credit limit 
        double calculatedCreditLimit = creditLimit - statementBalance;
        System.out.println("Your total credit limit is $"+ creditLimit + " each month");
        System.out.println("Your current credit limit: $" + String.format("%.2f", calculatedCreditLimit));
    }

    public void seeInterestRate() {
        interestRate = 0.30; // interest rate is 30% (APR)
        double monthlyRate = interestRate/12.0; // monthly APR rate calculation
        double totalInterest = statementBalance * monthlyRate; // calculating interest rate to balance
        statementBalance += totalInterest; // adding calculated interest to balance
        System.out.println("Your interest is: " + String.format("%.2f", totalInterest));
        System.out.println("You now own $" + String.format("%.2f", statementBalance) + " with interest rate included.");
    }

    public void dueDate() { 
        int gracePeriod = 23; // grace period (23 days) after billing cycle before due date
        dueDate = closingDate + gracePeriod; // due date is after grace period is over, which is after billing cycle is over (add billing cycle & grace period together)
        if (dueDate > 28) { // 28 days in a month, so if greater than 28 days, due date will be at 28 (max day)
            dueDate = 28;
        }
        System.out.println("Your credit card due date is: " + dueDate +"th");
    }

    public void minimumPayment() {
        double paymentPercent = statementBalance * 0.03; // 3% of balance
        System.out.println("Your minimum payment due for this month to avoid late fee is: $" + String.format("%.2f", paymentPercent)); // smallest payment due each month in order to have no late fee
    }

    // method for easier/faster printing for credit card
    @Override
    public String toString() {
        return "Credit card name: " + creditCardName + "\n" + 
        "Credit card number: " + creditCardNumber + "\n" +
        "Statement Balance: $" + String.format("%.2f", statementBalance) + "\n" +
        "Closing Date: " + closingDate +"th" + "\n" +
        "Cashback: $" + String.format("%.2f", cashBack) + "\n" +
        "Credit Limit: $" + String.format("%.2f", creditLimit) + "\n" +
        "Interest Rate: " + (interestRate * 100) +"%" + "\n" +
        "Due date: " + dueDate +"th" + "\n" +
        "Minimum payment: $" + String.format("%.2f", minimumPayment) + 
        "Purchase: " + purchases + "\n";
    }
}