import java.util.*;

public class FinanceManager {
    private String userAccounts;
    private String connectedAccounts;
    private String categories;
    private String recommendations;
    private String referrals;
    private int transactions;
    private int budgets;

    // Setters and Getters

    public FinanceManager(String userAccounts, String connectedAccounts, String categories, String referrals,
            int transactions, int budgets) {
        this.userAccounts = userAccounts;
        this.connectedAccounts = connectedAccounts;
        this.categories = categories;
        this.referrals = referrals;
        this.transactions = transactions;
        this.budgets = budgets;
    }

    public String getUserAccounts() {
        return userAccounts;
    }

    public String getConnectedAccounts() {
        return connectedAccounts;
    }

    public String getCategories() {
        return categories;
    }

    public String getReferrals() {
        return referrals;
    }

    public int getTransactions() {
        return transactions;
    }

    public int getBudgets() {
        return budgets;
    }

    // Methods

    public void budget(double monthlyBudget) {
        double totalExpenses = transactions;
        if (totalExpenses > monthlyBudget) {
            System.out.println("You are over the budget for this month by: " + (totalExpenses - monthlyBudget));
        } else {
            System.out.println("You are within the budget for this month: " + (monthlyBudget - totalExpenses));
        }
    }

    public void recommend() {
        if (transactions > budgets) {
            System.out.println("You are spending more than your budget. Consider cutting back on expenses.");
        } else if (transactions > budgets * 0.8) {
            System.out.println("You are getting close to your budget limit. Start paying attention to your cashflow.");
        } else {
            System.out.println("You are spending within budget. Good job!");
        }
    }

    public void categorize() {
        // Placeholder transactions, connect eventually from BankAccount
        List<String> transactionsList = Array.asList(
                "Grocery Costco $300",
                "Internet Xfinity $90",
                "Movie Demon Slayer $20",
                "Restaurant YGF Malatang $50");

    }

    public void connectAccount() {

    }

    public void refer() {

    }

    public static void main(String[] args) {

    }
}
