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
}