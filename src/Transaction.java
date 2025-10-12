public class Transaction {
    private String transactionType;
    private double amount;
    private String transactionSource;
    private String transactionDesc;
    private String transactionDate;

    // no arg default constructor
    public Transaction() {
        this.transactionType = "N/A";
        this.amount = 0.0;
        this.transactionSource = "N/A";
        this.transactionDesc = "N/A";
        this.transactionDate = "N/A";
    }
    // arg constructor
    public Transaction(String transactionType, double amount, String transactionSource, String transactionDesc, String transactionDate) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionSource = transactionSource;
        this.transactionDesc = transactionDesc;
        this.transactionDate = transactionDate;
    }

    // getters
    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
    
    @Override
    public String toString() {
        return "Transaction Date: " + transactionDate + "\n" +
        "Transaction Type: " + transactionType + "\n" + 
        "Amount: $"+String.format("%.2f", amount)+ "\n" +
        "Transaction Source: " + transactionSource + "\n" +
        "Transaction Description: " + transactionDesc + "\n";
    }
}