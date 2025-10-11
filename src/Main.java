import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Personal Finance Manager! Let's get you started!");
        System.out.println("Please enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Please create a username: ");
        String username = scanner.nextLine();
        System.out.println("Please create a password: ");
        String password = scanner.nextLine();
        System.out.println("Please enter your date of birth in MM/DD/YYYY format: ");
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
            CreditCard creditCard = new CreditCard();

            System.out.println("What credit card company do you have?");
            String cardCompany = scanner.nextLine();
            creditCard.setCreditCardName(cardCompany);

            System.out.println("What is your credit card number?");
            String cardNumber = scanner.nextLine();
            creditCard.setCreditCardNumber(cardNumber);

            System.out.println("What is your statement balance?");
            double currentBalance = scanner.nextDouble();
            creditCard.setStatementBalance(currentBalance);

        } else if (response.equals("Finance Manager")) {
            
        } else if (response.equals("Loan Tracker")) {
            
        } else {
            throw new IllegalArgumentException("Invalid user input. Please try again");
        }
    }
}
