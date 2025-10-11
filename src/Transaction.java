public class Transaction {
    private String transactionType;
    private double amount;
    private String transactionSource;
    private String transactionDesc;
    private String transactionDate;

    public Transaction(String transactionType, double amount, String transactionSource, String transactionDesc, String transactionDate) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionSource = transactionSource;
        this.transactionDesc = transactionDesc;
        this.transactionDate = transactionDate;
    }

    // getters
    public String transactionType() {
        return transactionType;
    }

    public double amount() {
        return amount;
    }

    public String transactionSource() {
        return transactionSource;
    }

    public String transactionDesc() {
        return transactionDesc;
    }

    public String transactionDate() {
        return transactionDate;
    }
    
    @Override
    public String toString() {
        return "Transaction Date: " + transactionDate + "\n" +
        "Transaction Type: " + transactionType + "\n" + 
        "Amount: $"+String.format("%.2f", amount)+ "\n" +
        "Transaction Source: " + transactionSource + "\n" +
        "Transaction Description: " + transactionSource + "\n";
    }
}
