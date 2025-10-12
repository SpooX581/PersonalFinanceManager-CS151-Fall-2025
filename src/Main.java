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
        System.out.println("Please create a new account");

        System.out.print("Date of Birth in YYYY-MM-DD format: ");
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

        user.createUser(dateofBirth, name, username, password);
        System.out.println("Thank you for registering!");

    }
    private static void menu() {
        System.out.println();
        System.out.println("Welcome to our Personal Finance Manager!");
        System.out.println("1. User Information");
        System.out.println("2. Bank Account");
        System.out.println("3. Credit Card");
        System.out.println("4. Finance Manager");
        System.out.println("5. Loan Tracker");
        System.out.println("6. Exit");
        System.out.println("Enter \"exit\" into any prompt to close.");
        System.out.println("Please type the option you would like to access: ");

    }

    public static void main(String[] args) {


        User user = new User();

        register(user);

        user.printUserInfo();

        boolean end = false;

        while(!end) {
            do {
                // Clear the screen before operation
                String input = scanner.nextLine();
                menu();
                exit(input);
                System.out.println();

                switch (input) {
                    case "2" -> {
                        System.out.println("Enter account number: ");
                        String accountNum = scanner.nextLine();
                        exit(accountNum);

                        System.out.println("Enter account PIN: ");
                        String PIN = scanner.nextLine();
                        exit(PIN);

                        System.out.println("Enter account balance: ");
                        double balance = Double.parseDouble(scanner.nextLine());

                        BankAccount bankAccount = new BankAccount(balance, PIN, accountNum);

                        System.out.println("Thanks for linking your account! Continue managing your account? (Y/N)");
                        String answer = scanner.nextLine();
                        exit(answer);
                        if(!answer.equalsIgnoreCase("Y")) {
                            System.out.println(" ");
                        } else {
                            bankAccount.accountMenu();
                            String manage = scanner.nextLine();

                            switch(manage) {
                                case "1" -> {
                                    double withdraw = scanner.nextDouble();
                                    bankAccount.withdraw(withdraw);
                                }

                                case "2" -> {
                                    double deposit = scanner.nextDouble();
                                    bankAccount.deposit(deposit);
                                }

                                case "3" -> {
                                    double transfer = scanner.nextDouble();
                                    bankAccount.transfer(transfer);
                                }

                                case "4" -> {
                                    double request = scanner.nextDouble();
                                    bankAccount.request(request);
                                }

                                default -> System.out.println("Invalid.");
                            }

                        }
                    }

                    case "6" -> {
                        exit("exit");
                    }


                    // default for invalid input
                    default -> System.out.println("Not an option, please select from the options above.");
                }

            } while (!end);

            scanner.close();
        }
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
         */