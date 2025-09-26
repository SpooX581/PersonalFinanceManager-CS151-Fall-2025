class LoanTracker {
    private String loan;
    private double amount;
    private double payment;
    private double totalPayments;

    public LoanTracker (String loan, double amount) {
        this.loan = loan;
        this.amount = amount;
        this.payment = payment;
    }

    public void projection(double amount, double payment, double totalPayments) {
        totalPayments = amount/payment;
        System.out.println("You have " + totalPayments + " remaining.");
    }
}