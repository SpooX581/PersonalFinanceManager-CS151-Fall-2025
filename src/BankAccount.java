public class BankAccount {
    private double balance;
    private String accountNum;
    private String accountPin;
    private String accountType; //checking or savings

    public BankAccount(double balance,String accountNum,String accountPin,String accountType){
        this.balance = balance;
        this.accountNum = accountNum;
        this.accountPin = accountPin;
        this.accountType = accountType;
    }

    public double getBalance(){
        return balance;
    }
    public String getAccountNum(){
        return accountNum;
    }

    public String getAccountPin(){
        return accountPin;
    }

    public String getAccountType(){
        return accountType;
    }

    public void withdraw(double amount){ 
        if(amount <= balance){ 
            balance = balance- amount;
            System.out.println("Accepted");
        }
        else{
            System.out.println("Declined");
        }
    }

    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("Amount deposited has to be positive");
        }
        else{
            balance = balance + amount;
            System.out.println("Ammount accepted");
        }
    }

    public void transfer(double amount){
        this.amount = amount;
    }

    public void request(double amount){
        this.amount = amount;
    }



    public static void main(String[] args) {
       
    }

}
