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
    private ArrayList<String> purchases = new ArrayList<>();

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
    public void setStatementBalance(double statementBalance) {
        this.statementBalance = statementBalance;
    }
    public void setCashBack(double cashBack) {
        this.cashBack = cashBack;
    }
    public void setMinimumPayment(double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    // methods
    public void checkStatementBalance() {
        System.out.println("Checking credit card balance " + statementBalance);
    } 

    public void makingAPurchase (String purchase, double amount) {
       if (amount > 0) {
        statementBalance += amount;
        purchases.add(purchase + ": $" + String.format("%.2f",amount));
       } else {
        System.out.println("Invalid purchase.");
       }
    }

    public void creditCardPayment (double amount) {
        if (amount > 0) {
            statementBalance -= amount;
        } else {
        System.out.println("Invalid payment amount.");
       }
    }

    public void showPurchases() {
        System.out.println("Your Purchases this Month: ");
        if (purchases.isEmpty()){
            System.out.println("No purchases exist. You have not made a purchase yet.");
        } else {
            for (String purchase : purchases) { 
            System.out.println(purchase);
        }
        }
    }

     public static void main(String[] args) {
        
    }
}

