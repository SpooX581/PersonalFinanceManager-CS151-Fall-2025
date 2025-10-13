import java.util.ArrayList;
public abstract class AbstractCreditCard {
    protected String creditCardName;
    protected String creditCardNumber;
    protected double statementBalance;
    protected int closingDate;
    protected double cashBack;
    protected double creditLimit;
    protected double interestRate;
    protected int dueDate;
    protected double minimumPayment;
    protected ArrayList<String> purchases = new ArrayList<>();

    public AbstractCreditCard(String creditCardName, String creditCardNumber) {
        this.creditCardName = creditCardName;
        this.creditCardNumber = creditCardNumber;
        this.statementBalance = 0.0;
        this.closingDate = 20;
        this.cashBack = 0.0;
        this.creditLimit = 5000.0;
        this.interestRate = 0.0;
        this.dueDate = 0;
        this.minimumPayment = 0;
    }
    
    public String getCreditCardName() {
        return creditCardName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getStatementBalance() {
        return statementBalance;
    }

    public abstract void makingAPurchase(String purchase, double amount);
    public abstract void creditCardPayment(double paymentAmount);
    public abstract void dueDate();
    public abstract void minimumPayment();
}
