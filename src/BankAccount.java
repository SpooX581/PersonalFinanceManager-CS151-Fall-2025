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

    public static void main(String[] args) {
       
    }

}
