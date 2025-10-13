public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount test = new BankAccount(500,"17172" , "192112");

        //Balance
        System.out.println(test.getBalance());
        //AccountNum
        System.out.println(test.getAccountNum());
        //AccountPin
        System.out.println(test.getAccountPin());

        //Request
        //Positive
        test.request(200);
        //Nonpositive
        test.request(0);

        //Withdraw
        BankAccount withdrawLess = new BankAccount(500,"17173" , "192138");
        BankAccount withdrawEqual = new BankAccount(500,"17174" , "9235412");
        BankAccount withdrawGreater = new BankAccount(500,"17175" , "853212");
        //Amount less than balance
        withdrawLess.withdraw(300);
        //Amount equal to balance
        withdrawEqual.withdraw(500);
        //Amount greater than balance
        withdrawGreater.withdraw(600);

        //Deposit
        BankAccount depositPositive = new BankAccount(500,"171233" , "19322138");
        BankAccount depositNonPositive = new BankAccount(500,"1723474" , "92335412");
        //Amount not positive
        depositNonPositive.deposit(0);
        //Amount positive
        depositPositive.deposit(60);

        //Transfer
        BankAccount transferNonPositive = new BankAccount(500,"173254173" , "1932138");
        BankAccount transferPositiveLessThanEqualBalance = new BankAccount(500,"145346374" , "923545212");
        BankAccount transferPositiveGreaterThan = new BankAccount(500,"17232175" , "853325212");
        //Transfer non positive
        transferNonPositive.transfer(0);
        //Transfer positive number less than/equal to balance
        transferPositiveLessThanEqualBalance.transfer(300);
        //Transfer positive number greater than balance
        transferPositiveGreaterThan.transfer(600);  
    }
    
}
