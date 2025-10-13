import java.util.*;

public class DataStorage {
    // userId -> their bank account(s) by accountNum
    private final Map<String, Map<String, BankAccount>> bankAccountsByUser = new HashMap<>();
    // userId -> their credit cards by cardNumber
    private final Map<String, Map<String, CreditCard>> creditCardsByUser = new HashMap<>();

    public void addBankAccount(String userId, BankAccount account) {
        bankAccountsByUser.computeIfAbsent(userId, k -> new HashMap<>())
                .put(account.getIdNumber(), account);
    }

    public void addCreditCard(String userId, CreditCard card) {
        creditCardsByUser.computeIfAbsent(userId, k -> new HashMap<>())
                .put(card.getCreditCardNumber(), card);
    }

    public Map<String, BankAccount> getBankAccounts(String userId) {
        return bankAccountsByUser.getOrDefault(userId, Collections.emptyMap());
    }

    public Map<String, CreditCard> getCreditCards(String userId) {
        return creditCardsByUser.getOrDefault(userId, Collections.emptyMap());
    }

    @Override
    public String toString() {
        return "DataStorage:\n" +
                "Total Users with Bank Accounts: " + bankAccountsByUser.size() + "\n" +
                "Total Users with Credit Cards: " + creditCardsByUser.size() + "\n";
    }
}
