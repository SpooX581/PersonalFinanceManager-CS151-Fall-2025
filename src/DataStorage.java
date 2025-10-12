import java.util.HashMap;

public class DataStorage {
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNum(), account);
    }

    public BankAccount getAccount(String accountNum) {
        return accounts.get(accountNum);
    }

}
