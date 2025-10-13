import java.util.ArrayList;

public class CreditCard extends AbstractCreditCard {
    // Subclass keeps same fields (already defined protected in base), fine to
    // mirror.
    // Ensure constructors set them so getters return non-null.

    // Constructor with validation
    public CreditCard(String name, double limit) throws InvalidCreditLimitException {
        super(name, "");
        if (limit <= 0) {
            throw new InvalidCreditLimitException("Credit limit must be greater than 0.");
        }
        this.creditCardName = name; // set subclass fields
        this.creditCardNumber = ""; // consistent with super call
        this.creditLimit = limit;
        this.statementBalance = 0.0;
        this.closingDate = 20;
        this.cashBack = 0.0;
        this.interestRate = 0.0;
        this.dueDate = 0;
        this.minimumPayment = 0.0;
    }

    // no arg/default constructor with default values
    public CreditCard(String creditCardName, String creditCardNumber) {
        super(creditCardName, creditCardNumber);
        this.creditCardName = creditCardName; // set subclass fields
        this.creditCardNumber = creditCardNumber;
        this.statementBalance = 0.0;
        this.closingDate = 20;
        this.cashBack = 0.0;
        this.creditLimit = 5000.0;
        this.interestRate = 0.0;
        this.dueDate = 0;
        this.minimumPayment = 0.0;
    }

    // arg constructor
    public CreditCard(String creditCardName, String creditCardNumber, double statementBalance, int closingDate,
            double cashBack, double creditLimit, double interestRate, int dueDate, double minimumPayment) {
        super(creditCardName, creditCardNumber);
        this.creditCardName = creditCardName; // set subclass fields
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

    public double getCashBack() {
        return cashBack;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public double getMinimumPayment() {
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
        System.out.println("Checking credit card balance: $" + String.format("%.2f", statementBalance));
    }

    @Override
    public void makingAPurchase(String purchase, double amount)
            throws NegativeAmountException, ExceedCreditLimitException {
        if (amount < 0) {
            throw new NegativeAmountException("Purchase amount cannot be negative.");
        }
        if (statementBalance + amount > creditLimit) {
            throw new ExceedCreditLimitException(
                    "Purchase denied: exceeds credit limit of $" + creditLimit);
        }

        statementBalance += amount;
        purchases.add(purchase + ": $" + String.format("%.2f", amount));
        double cashBackRate = 0.03; // 3% cashback
        double cashBackEarned = amount * cashBackRate;
        cashBack += cashBackEarned;
        System.out.println("You earned $" + String.format("%.2f", cashBackEarned) + " cashback on this purchase.");
    }

    @Override
    public void creditCardPayment(double paymentAmount) {
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
        if (purchases.isEmpty()) {
            System.out.println("No purchases currently exist. You have not made a purchase yet.");
        } else {
            for (String purchaseList : purchases) {
                System.out.println(purchaseList);
            }
        }
    }

    public void seeClosingDate() {
        closingDate = 20; // always on the 20th day of the month
        System.out.println("Your closing date is the " + closingDate + "th day of every month.");
    }

    public void seeCashBack() {
        System.out.println("Total cashback this month: $" + String.format("%.2f", cashBack));
    }

    public void seeCreditLimit() {
        // Do not overwrite the configured limit each time
        double calculatedCreditLimit = creditLimit - statementBalance;
        System.out.println("Your total credit limit is $" + String.format("%.2f", creditLimit) + " each month");
        System.out.println("Your current credit limit: $" + String.format("%.2f", calculatedCreditLimit));
    }

    public void seeInterestRate() {
        interestRate = 0.30; // interest rate is 30% (APR)
        double monthlyRate = interestRate / 12.0;
        double totalInterest = statementBalance * monthlyRate;
        statementBalance += totalInterest;
        System.out.println("Your interest is: " + String.format("%.2f", totalInterest));
        System.out.println("You now own $" + String.format("%.2f", statementBalance) + " with interest rate included.");
    }

    @Override
    public void dueDate() {
        int gracePeriod = 23; // grace period (23 days) after billing cycle before due date
        dueDate = closingDate + gracePeriod;
        if (dueDate > 28) {
            dueDate = 28;
        }
        System.out.println("Your credit card due date is: " + dueDate + "th");
    }

    @Override
    public void minimumPayment() {
        double paymentPercent = statementBalance * 0.03; // 3% of balance
        minimumPayment = paymentPercent; // store so toString() shows real value
        System.out.println("Your minimum payment due for this month to avoid late fee is: $"
                + String.format("%.2f", paymentPercent));
    }

    @Override
    public String toString() {
        return "Credit card name: " + creditCardName + "\n" +
                "Credit card number: " + creditCardNumber + "\n" +
                "Statement Balance: $" + String.format("%.2f", statementBalance) + "\n" +
                "Closing Date: " + closingDate + "th" + "\n" +
                "Cashback: $" + String.format("%.2f", cashBack) + "\n" +
                "Credit Limit: $" + String.format("%.2f", creditLimit) + "\n" +
                "Interest Rate: " + (interestRate * 100) + "%" + "\n" +
                "Due date: " + dueDate + "th" + "\n" +
                "Minimum payment: $" + String.format("%.2f", minimumPayment) +
                "Purchase: " + purchases + "\n";
    }

    public void creditMenu() {
        System.out.println();
        System.out.println("Continue managing credit card");
        System.out.println("1. Make a purchase");
        System.out.println("2. Make a payment");
        System.out.println("3. See purchases");
        System.out.println("4. View all credit card information");
    }
}
