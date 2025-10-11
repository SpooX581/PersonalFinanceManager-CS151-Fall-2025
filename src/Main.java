package src;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static void clear() {
        // function to clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void exit(String exit) {
        // function to exit the program
        if (exit.toLowerCase().equals("exit")) {
            System.out.println("Thanks for using our Personal Finance Manager!");
            System.exit(0);
        }
    }

    private static void register(User user) {
        // Function to prompt registration
        System.out.println("Welcome to our Personal Finance Manager! Let's get you started.");
        System.out.println("Create a new account");

        System.out.println("Date of Birth in YYYY-MM-DD format:");
        String dateofBirth = scanner.nextLine();
        exit(dateofBirth);
        System.out.println();

        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        exit(name);
        System.out.println();

        System.out.print("Username: ");
        String username = scanner.nextLine();
        exit(username);
        System.out.println();

        System.out.print("Create a new password: ");
        String password = scanner.nextLine();
        exit(password);
        System.out.println();

        String newUser = user.createUser(dateofBirth, name, username, password);
        System.out.println("Thank you for registering!");

    }


    public static void main(String[] args) {
        User user = new User();
        register(user);
    }
}
        /*
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
        System.out.println("1. User Information");
        System.out.println("2. Bank Account");
        System.out.println("3. Credit Card");
        System.out.println("4. Finance Manager");
        System.out.println("5. Loan Tracker");
        System.out.println("6. Exit");

        String response = scanner.nextLine();
        if (response.equals("1") ||response.equals("User Information")) {

        } else if (response.equals("2") || response.equals("Bank Account")) {
            
        } else if (response.equals("3") || response.equals("Credit Card")) {
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

        } else if (response.equals("4") || response.equals("Finance Manager")) {
            
        } else if (response.equals("5") || response.equals("Loan Tracker")) {
            
        } else if (response.equals("6") || response.equals("Exit") || response.equals("exit")) {
            System.out.println("Thank you for coming today, have a good rest of your day!");
            System.exit(0);
        } else {
            throw new IllegalArgumentException("Invalid user input. Please try again");
        }
        scanner.close();
    }
}
         */