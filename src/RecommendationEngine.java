import java.util.*;
import java.util.stream.Collectors;

public class RecommendationEngine {
    public static class Bill {
        public final String name;
        public final double amount;
        public final Integer dueDay;

        public Bill(String name, double amount, Integer dueDay) {
            this.name = name;
            this.amount = amount;
            this.dueDay = dueDay;
        }
    }

    // How much the spend is for this month
    private final List<Transaction> transactions;

    // User's budget
    private final double monthlyBudget;

    // Reads cashback from credit cards and limits
    private final List<CreditCard> creditCards;

    // Key bills to allocate
    private final List<Bill> monthlyBills;

    public RecommendationEngine(List<Transaction> transactions, double monthlyBudget, List<CreditCard> creditCards,
            List<Bill> monthlyBills) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();
        this.monthlyBudget = Math.max(0, monthlyBudget);
        this.creditCards = creditCards != null ? creditCards : new ArrayList<>();
        this.monthlyBills = monthlyBills != null ? monthlyBills : new ArrayList<>();
    }

    // Spending alerts vs budget
    public void spendingAlerts() {
        double totalSpent = transactions.stream()
                .filter(t -> "DEBIT".equalsIgnoreCase(t.getTransactionType())
                        || "PURCHASE".equalsIgnoreCase(t.getTransactionType()))
                .mapToDouble(Transaction::getAmount).sum();

        System.out.println("n=== Spending vs budget ===");
        System.out.printf("Month-to-date spend: $%.2f | Budget $%.2f%n", totalSpent, monthlyBudget);

        if (monthlyBudget <= 0) {
            System.out.println("No monthly budget set. Set a budget to get alerts.");
            return;
        }

        double utilization = totalSpent / monthlyBudget;

        if (utilization >= 1.10) {
            System.out.println(
                    "Warning. You are ~10%+ over budget. Consider a 1-2 week freeze on discretionary categories (dining/entertainment/etc). ");
        } else if (utilization >= 1.00) {
            System.out.println(
                    "Danger. You are over budget. Cut back on non-essentials and prioritize upcoming bills (see below). ");
        } else if (utilization >= 0.80) {
            System.out.println("Heads up: you've passed 80% of your budget.");
        } else {
            System.out.println(
                    "Great job, you're on track! Keep fixed costs steady and continue to be mindful of purchases");
        }
    }

    // Cashback summary by credit card (uses CreditCard.cashback filed)
    public void cashbackSummary() {
        System.out.println("\n=== Cashback Summary ===");

        if (creditCards.isEmpty()) {
            System.out.println("No credit cards on file.");
            return;
        }

        double totalCB = 0.0;

        for (CreditCard card : creditCards) {

            // You already track cashBack as you add purchases
            double cb = card.getCashBack();
            totalCB += cb;
            System.out.printf("%s: Cashback this month $%.2f (Statement Balance $%.2f, Limit $%.2f)%n",
                    card.getCreditCardName(), cb, card.getStatementBalance(), card.getCreditLimit());
        }
        System.out.printf("Total cashback across cards: $%.2f%n", totalCB);
    }

    // Bills to focus on and recommend allocation plan
    public void billsFocusPlan() {
        System.out.println("\n=== Bills to Prioritize ===");
        if (monthlyBills.isEmpty()) {
            System.out.println("No bills on file. Add bills to get a prioritized plan.");
            return;
        }

        // Sorts through through earliest due day first, unknown last
        List<Bill> ordered = monthlyBills.stream()
                .sorted(Comparator.comparing(b -> b.dueDay == null ? Integer.MAX_VALUE : b.dueDay))
                .collect(Collectors.toList());

        double totalBills = ordered.stream().mapToDouble(b -> b.amount).sum();
        System.out.printf("Total upcoming bills: $%.2f%n", totalBills);

        System.out.println("Priority order (soonest due first):");
        for (Bill b : ordered) {
            String dueStr = (b.dueDay == null) ? "due: unknown" : ("due: " + b.dueDay);
            System.out.printf("• %-18s  $%7.2f   (%s)%n", b.name, b.amount, dueStr);
        }

        // Suggest: allocate enough cash to cover earliest bills first.
        System.out.println("\nSuggested allocation strategy:");
        System.out.println("1) Cover all essentials (rent, utilities, minimum card payments) first.");
        System.out.println(
                "2) If short on cash, pay minimums on CCs and avoid interest by paying the card with nearest due date next.");
        System.out.println("3) Keep a small buffer ($50-$150) for groceries/fuel before discretionary spend.");
    }

    // Convenience wrapper to run all three parts
    public void runAll() {
        spendingAlerts();
        cashbackSummary();
        billsFocusPlan();
        System.out.println("\n(End of recommendations) \n");
    }
}