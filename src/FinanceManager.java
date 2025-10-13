import java.util.*;

public class FinanceManager {
    private final User user;
    private final DataStorage dataStorage;

    private double monthlyBudget;
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<CreditCard> creditCards = new ArrayList<>();

    public FinanceManager(User user, DataStorage dataStorage) {
        this.user = user;
        this.dataStorage = dataStorage;
    }

    // Setup
    public void loadUserAccounts() {
        Map<String, BankAccount> bankAccounts = dataStorage.getBankAccounts(user.getIdNumber());
        Map<String, CreditCard> cards = dataStorage.getCreditCards(user.getIdNumber());

        transactions.clear();
        for (BankAccount b : bankAccounts.values()) {
            transactions.addAll(b.getTransactions());
        }

        creditCards.clear();
        creditCards.addAll(cards.values());
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = Math.max(0, monthlyBudget);
    }

    // Expose for UI Screens
    public void showUserSummary() {
        user.printUserInfo();
        System.out.println("\nBank Accounts:");
        Map<String, BankAccount> myBanks = dataStorage.getBankAccounts(user.getIdNumber());
        if (myBanks.isEmpty())
            System.out.println("  (none)");
        else
            myBanks.values().forEach(a -> System.out.printf("  %s | Balance: $%.2f (Txns: %d)%n",
                    a.getIdNumber(), a.getMoneyValue(), a.getTransactions().size()));

        System.out.println("\nCredit Cards:");
        Map<String, CreditCard> myCards = dataStorage.getCreditCards(user.getIdNumber());
        if (myCards.isEmpty())
            System.out.println("  (none)");
        else
            myCards.values().forEach(c -> {
                System.out.println("  " + c.getCreditCardName() + " - " + c.getCreditCardNumber());
                c.seeStatementBalance();
                c.seeCashBack();
                c.seeCreditLimit();
            });
    }

    public void runRecommendations() {
        List<RecommendationEngine.Bill> bills = List.of(
                new RecommendationEngine.Bill("Rent", 1200, 1),
                new RecommendationEngine.Bill("Internet", 60, 5),
                new RecommendationEngine.Bill("Electric", 90, 10),
                new RecommendationEngine.Bill("Phone", 45, 15));
        RecommendationEngine engine = new RecommendationEngine(transactions, monthlyBudget, creditCards, bills);
        engine.runAll();
    }

    public void analyzeBudgetAndOptionallyAddExpense(Scanner scanner) {
        System.out.print("Add a quick expense now? (y/n): ");
        String yn = scanner.nextLine().trim();
        if (yn.equalsIgnoreCase("y")) {
            System.out.print("Type (DEBIT/PURCHASE): ");
            String t = scanner.nextLine();
            double amt = askDouble(scanner, "Amount: $");
            System.out.print("Source/Merchant: ");
            String src = scanner.nextLine();
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            transactions.add(new Transaction(t, amt, src, desc, date));
            System.out.println("Added.\n");
        }

        double sumSpent = transactions.stream()
                .filter(tx -> "DEBIT".equalsIgnoreCase(tx.getTransactionType())
                        || "PURCHASE".equalsIgnoreCase(tx.getTransactionType()))
                .mapToDouble(Transaction::getAmount).sum();
        BudgetPlanner planner = new BudgetPlanner(monthlyBudget, sumSpent);
        planner.manageBudget();
    }

    public void generateMonthlyReport() {
        ArrayList<Double> spent = new ArrayList<>();
        ArrayList<String> txStrs = new ArrayList<>();

        double totalSpent = 0.0;
        for (Transaction tx : transactions) {
            if ("DEBIT".equalsIgnoreCase(tx.getTransactionType())
                    || "PURCHASE".equalsIgnoreCase(tx.getTransactionType())) {
                totalSpent += tx.getAmount();
                txStrs.add(tx.toString());
            }
        }
        spent.add(totalSpent);

        ReportGenerator rg = new ReportGenerator(
                user.getFullName(), "N/A", 0, 0, "", 0, 0, 0, 0);
        rg.calculateTotalMonthSpent(spent);
        rg.calculateTotalMonthTransactions(txStrs);

        ArrayList<Double> totalBalance = new ArrayList<>();
        totalBalance.add(totalSpent);
        rg.calculateTotalMonthBalance(totalBalance);

        ArrayList<Double> cashBack = new ArrayList<>();
        cashBack.add(totalSpent * 0.03);
        rg.calculateTotalMonthCashBack(cashBack);

        System.out.println("\n=== Monthly Report ===");
        System.out.println(rg);
    }

    // Utilities
    private static double askDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
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
}
