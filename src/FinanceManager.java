public class FinanceManager {
    private String userAccounts;
    private String connectedAccounts;
    private String categories;
    private String recommendations;
    private String referrals;
    private int transactions;
    private int budgets;

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
}
