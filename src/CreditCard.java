public class CreditCard {
    private String creditCardName;
    private double statementBalance;
    private int closingDate;
    private double cashBack;
    private double creditLimit;
    private double interestRate;
    private int dueDate;
    private double minimumPayment;

    public CreditCard (String creditCardName, double statementBalance, int closingDate, double cashBack, double creditLimit, double interestRate, int dueDate, double minimumPayment){
        this.creditCardName = creditCardName;
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
    public double getStatementBalance() {
        return statementBalance;
    }
    public int closingDate() {
       return closingDate;
    }
    public double cashBack(){
        return cashBack;
    }
    public double creditLimit(){
        return creditLimit;
    }
    public double interestRate() {
        return interestRate;
    }
    public int dueDate() {
        return dueDate;
    }
    public double minimumPayment(){
        return minimumPayment;
    }
    // setters
    public void setStatementBalance(double statementBalance) {
        this.statementBalance = statementBalance;
    }
    public void setCashBack(double cashBack) {
        this.cashBack = cashBack;
    }
    public void setMinimumPayment(double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }
     public static void main(String[] args) {
       
    }
}

