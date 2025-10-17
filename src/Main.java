import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static void pause() {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    private static boolean isValidName(String name) {

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }

        return true;
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
                    System.out.print("Full Name Test: ");
                    String name = scanner.nextLine();
                    boolean validName = false;
                    while (!validName) {
                        if (name.isEmpty()){
                            System.out.print("Full Name cannot be empty. Please enter again: ");
                            name = scanner.nextLine();
                        } else if (!isValidName(name)) {
                            System.out.print("Full Name cannot contain numbers. Please enter again: ");
                            name = scanner.nextLine();
                        } else {
                            validName = true;
                        }
                    }
                    if (name.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    if (username.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Password: ");
                    String password = scanner.next();
                    boolean validPassword = false;
                    while (!validPassword) {
                        if (password.length() < 6) {
                            System.out.print("Password must be at least 6 characters long. Please enter again: ");
                            password = scanner.nextLine();
                            validPassword = true;
                        } else if (password.chars().noneMatch(Character::isDigit)) {
                            System.out.print("Password must contain at least one number. Please enter again: ");
                            password = scanner.nextLine();
                        } else if (password.chars().noneMatch(Character::isUpperCase)) {
                            System.out.print("Password must contain at least one Upper case character. Please enter again: ");
                            password = scanner.nextLine();
                        } else {
                            System.out.println("Valid password accpeted.");
                            validPassword = true;
                        }
                    }
                    if (password.equalsIgnoreCase("exit"))
                        System.exit(0);
                    System.out.print("Date of Birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    boolean validDate = false;
                    while (!validDate) {
                        if (dob.isEmpty()){
                            System.out.print("Date of birth cannot be empty. Please enter again: ");
                            dob = scanner.nextLine();
                        } else {
                            validDate = true;
                        }
                    }
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
        FileDb.writeSampleBankDb(user.getIdNumber());
        FileDb.writeSampleCreditDb(user.getIdNumber());

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
