import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static void pause() {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    private static double askDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using our Personal Finance Manager!");
                System.exit(0);
            }
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static AuthService.AuthResult handleAuth() {
        System.out.println("""
                ========================================
                Welcome to the Personal Finance Manager
                ========================================
                1) Register
                2) Login
                (Enter "exit" anytime to quit)
                """);
        while (true) {
            System.out.print("Choose 1 or 2: ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            switch (choice) {
                case "1" -> {
                    System.out.print("Full Name: ");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    if (username.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    if (password.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Date of Birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    if (dob.equalsIgnoreCase("exit"))
                        System.exit(0);

                    var res = AuthService.register(name, username, password, dob);
                    System.out.println(res.message);
                    if (res.success)
                        return res;
                }
                case "2" -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    if (username.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    if (password.equalsIgnoreCase("exit"))
                        System.exit(0);

                    var res = AuthService.login(username, password);
                    System.out.println(res.message);
                    if (res.success)
                        return res;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 1) Auth
        var auth = handleAuth();
        User user = auth.user;
        System.out.println();
        user.printUserInfo();

        // 2) Prepare demo DB for this user in resources/ and load it
        FileDb.writeSampleBankDb(user.getUserId());
        FileDb.writeSampleCreditDb(user.getUserId());

        DataStorage store = new DataStorage();
        FileDb.loadAllInto(store);

        // 3) Create FinanceManager and load accounts/txns
        FinanceManager manager = new FinanceManager(user, store);
        manager.loadUserAccounts();

        // 4) Ask budget
        double monthlyBudget = askDouble("\nEnter your monthly budget: $");
        manager.setMonthlyBudget(monthlyBudget);

        // 5) Main menu loop
        while (true) {
            System.out.println("""

                    ============ Main Menu ============
                    1. User Information
                    2. Recommendation Engine
                    3. Budget Planner
                    4. Report Generator
                    5. Loan Tracker (stub)
                    6. Exit
                    """);
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("exit") || "6".equals(choice)) {
                System.out.println("Thanks for using our Personal Finance Manager!");
                break;
            }

            switch (choice) {
                case "1" -> {
                    manager.showUserSummary();
                    pause();
                }
                case "2" -> {
                    manager.runRecommendations();
                    pause();
                }
                case "3" -> {
                    manager.analyzeBudgetAndOptionallyAddExpense(scanner);
                    pause();
                }
                case "4" -> {
                    manager.generateMonthlyReport();
                    pause();
                }
                case "5" -> {
                    System.out.print("Enter loan name: ");
                    String loanName = scanner.nextLine();
                    double amount = askDouble("Enter loan amount: $");
                    double payment = askDouble("Enter monthly payment: $");
                    double total = amount / payment;

                    LoanTracker loan = new LoanTracker(loanName, amount, total);
                    loan.projection(amount, payment, total);
                    pause();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

/**
 * private static void register(User user) {
 * // Function to prompt registration
 * System.out.println("Welcome to our Personal Finance Manager! Let's get you
 * started.");
 * System.out.println("Please create a new account");
 * 
 * System.out.print("Date of Birth in YYYY-MM-DD format: ");
 * String dateofBirth = scanner.nextLine();
 * exit(dateofBirth);
 * System.out.println();
 * 
 * System.out.print("Full Name: ");
 * String name = scanner.nextLine();
 * exit(name);
 * System.out.println();
 * 
 * System.out.print("Username: ");
 * String username = scanner.nextLine();
 * exit(username);
 * System.out.println();
 * 
 * System.out.print("Create a new password: ");
 * String password = scanner.nextLine();
 * exit(password);
 * System.out.println();
 * 
 * user.createUser(dateofBirth, name, username, password);
 * System.out.println("Thank you for registering!");
 * 
 * }
 * 
 * private static void menu() {
 * System.out.println();
 * System.out.println("Welcome to our Personal Finance Manager!");
 * System.out.println("1. User Information");
 * System.out.println("2. Bank Account");
 * System.out.println("3. Credit Card");
 * System.out.println("4. Finance Manager");
 * System.out.println("5. Loan Tracker");
 * System.out.println("6. Exit");
 * System.out.println("Enter \"exit\" into any prompt to close.");
 * System.out.println("Please type the option you would like to access: ");
 * 
 * }
 * 
 * public static void main(String[] args) {
 * 
 * User user = new User();
 * 
 * register(user);
 * 
 * user.printUserInfo();
 * 
 * boolean end = false;
 * 
 * while (!end) {
 * do {
 * menu();
 * String input = scanner.nextLine();
 * exit(input);
 * System.out.println();
 * 
 * switch (input) {
 * case "2" -> {
 * System.out.println("Enter account number: ");
 * String accountNum = scanner.nextLine();
 * exit(accountNum);
 * 
 * System.out.println("Enter account PIN: ");
 * String PIN = scanner.nextLine();
 * exit(PIN);
 * 
 * System.out.println("Enter account balance: ");
 * double balance = Double.parseDouble(scanner.nextLine());
 * 
 * BankAccount bankAccount = new BankAccount(balance, PIN, accountNum);
 * 
 * System.out.println("Thanks for linking your account! Continue managing your
 * account? (Y/N)");
 * String answer = scanner.nextLine();
 * exit(answer);
 * if (!answer.equalsIgnoreCase("Y")) {
 * System.out.println(" ");
 * } else {
 * bankAccount.accountMenu();
 * String manage = scanner.nextLine();
 * 
 * switch (manage) {
 * case "1" -> {
 * System.out.print("Enter withdrawal amount: ");
 * double withdraw = scanner.nextDouble();
 * bankAccount.withdraw(withdraw);
 * }
 * 
 * case "2" -> {
 * System.out.print("Enter deposit amount: ");
 * double deposit = scanner.nextDouble();
 * bankAccount.deposit(deposit);
 * }
 * 
 * case "3" -> {
 * System.out.println("Enter transfer amount: ");
 * double transfer = scanner.nextDouble();
 * bankAccount.transfer(transfer);
 * }
 * 
 * case "4" -> {
 * System.out.println("Enter request amount: ");
 * double request = scanner.nextDouble();
 * bankAccount.request(request);
 * }
 * 
 * default -> System.out.println("Invalid.");
 * }
 * 
 * }
 * }
 * 
 * case "3" -> {
 * System.out.print("Enter credit card name: ");
 * String creditCardName = scanner.nextLine();
 * exit(creditCardName);
 * 
 * System.out.println("Enter credit card number: ");
 * String creditCardNumber = scanner.nextLine();
 * exit(creditCardNumber);
 * 
 * CreditCard creditCard = new CreditCard(creditCardName, creditCardNumber);
 * 
 * System.out.println("Thanks for linking your card! Continue managing your
 * credit card? (Y/N)");
 * String answer = scanner.nextLine();
 * exit(answer);
 * if (!answer.equalsIgnoreCase("Y")) {
 * System.out.println(" ");
 * } else {
 * creditCard.creditMenu();
 * String manage = scanner.nextLine();
 * 
 * switch (manage) {
 * case "1" -> {
 * System.out.print("Enter purchase name: ");
 * String purchase = scanner.nextLine();
 * exit(purchase);
 * System.out.print("Enter purchase amount: ");
 * double amount = scanner.nextDouble();
 * creditCard.makingAPurchase(purchase, amount);
 * }
 * 
 * case "2" -> {
 * System.out.println("Enter payment amount: ");
 * double payment = scanner.nextDouble();
 * creditCard.creditCardPayment(payment);
 * }
 * 
 * case "3" -> {
 * creditCard.seePurchases();
 * }
 * 
 * case "4" -> {
 * creditCard.toString();
 * }
 * 
 * default -> System.out.println("Invalid.");
 * }
 * 
 * }
 * }
 * 
 * case "6" -> {
 * exit("exit");
 * }
 * 
 * // default for invalid input
 * default -> System.out.println("Not an option, please select from the options
 * above.");
 * }
 * 
 * } while (!end);
 * 
 * scanner.close();
 * }
 * }
 * }
 **/
