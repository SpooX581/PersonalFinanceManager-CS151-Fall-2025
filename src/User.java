import java.util.Scanner;

public class User {
    private String fullName;
    private String username;
    private String password;
    private String dateOfBirth;
    private double networth;
    private int creditCards;
    private int bankAccounts;
    private String idNumber;

    public User (String fullName, String username, String password, String dateOfBirth, double networth, int creditCards, int bankAccounts, String idNumber) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.networth = networth;
        this.creditCards = creditCards;
        this.bankAccounts = bankAccounts;
        this.idNumber = idNumber;
    }
    // getters
    public String getFullName(String fullName) {
        return fullName;
    }
    public String getUsername(String username) {
        return username;
    }
    public String getPassword(String password) {
        return password;
    }
    public String getDateOfBirth(String dateOfBirth) {
        return dateOfBirth;
    }
    public double getNetworth(double networth) {
        return networth;
    }
    public int getCreditCards(int creditCards) {
        return creditCards;
    }
    public int getBankAccounts(int bankAccounts) {
        return bankAccounts;
    }
    public String idNumber(String idNumber) {
        return idNumber;
    }
    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNetworth(double networth) {
        this.networth = networth;
    }
    public void setCreditCards(int creditCards) {
        this.creditCards = creditCards;
    }
    public void setBankAccounts(int bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // account creation
        System.out.println("Welcome to our Personal Finance Manager!");
        System.out.println("What is your full name? Please enter: ");
        String fullName = scanner.nextLine();
        System.out.println("Now, please create a username: ");
        String username = scanner.nextLine();
        System.out.println("Please create your password: ");
        String password = scanner.nextLine();
        System.out.println("What is your date of birth? Please enter in MM/DD/YYYY format.");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Account creation successful " + fullName + "! Remember to keep your information safe.");
        System.out.println("Please create an ID number.");
        String idNumber = scanner.nextLine();

        // finance manager system menu
        System.out.println("Welcome back, " + fullName + "!");
        System.out.println("Please type the option you would like to access: ");
        System.out.println("User Information");
        System.out.println("Bank Account");
        System.out.println("Credit Card");
        System.out.println("Finance Manager");
        System.out.println("Loan Tracker");

        String response = scanner.nextLine();
        if (response.equals("User Information")) {

        } else if (response.equals("Bank Account")) {
            
        } else if (response.equals("Credit Card")) {
            
        } else if (response.equals("Finance Manager")) {
            
        } else if (response.equals("Loan Tracker")) {
            
        } else {
            throw new IllegalArgumentException("Invalid user input. Please try again");
        }
    }
}