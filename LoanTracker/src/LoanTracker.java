class LoanTracker {
    private String loan;
    private double amount;
    private double payment;
    private double totalPayments;

    public LoanTracker (String loan, double amount, double totalPayments) {
        this.loan = loan;
        this.amount = amount;
        this.payment = payment;
        this.totalPayments = totalPayments;
    }

    public String getLoan(){
        return loan;
    }

    public double getAmount(){
        return amount;
    }

    public double payment(){
        return payment;
    }

    public double totalPayments(){
        return totalPayments;
    }

    public void projection(double amount, double payment, double totalPayments) {
        totalPayments = amount/payment;
        System.out.println("You have " + totalPayments + " remaining.");
    }
}