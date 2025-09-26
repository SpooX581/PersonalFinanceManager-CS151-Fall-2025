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

    public double getTotalPayments(){
        return totalPayments;
    }

    public void setLoan(){
        this.loan = loan;
    }

    public void setAmount(){
        this.amount = amount;
    }

    public void setPayment(){
        this.payment = payment;
    }

    public void setTotalPayments(){
        this.totalPayments = totalPayments;
    }

    public void projection(double amount, double payment, double totalPayments){
        totalPayments = amount/payment;
        System.out.println("You have " + totalPayments + " payments remaining.");
    }

}