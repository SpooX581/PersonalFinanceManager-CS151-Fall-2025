import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileDb {
    // Put data files under a separate resources/ folder
    public static final String BASE_DIR = "resources";
    public static final String BANK_DB = "bank_db.txt";
    public static final String CC_DB = "credit_db.txt";

    private static Path ensureResourcesDir() throws IOException {
        Path dir = Paths.get(BASE_DIR);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        return dir;
    }

    private static Path bankPath() throws IOException {
        return ensureResourcesDir().resolve(BANK_DB);
    }

    private static Path creditPath() throws IOException {
        return ensureResourcesDir().resolve(CC_DB);
    }

    /**
     * Create sample BANK DB for the current user (writes to resources/bank_db.txt).
     */
    public static void writeSampleBankDb(String userId) throws IOException {
        String content = """
                # ==== BANK_DB ====
                # Header: USER_ID,ACCOUNT_NUM,ACCOUNT_PIN,BALANCE
                ACCOUNT,%s,111222333,4321,1500.00

                # Transactions: USER_ID,ACCOUNT_NUM,DATE,TYPE,AMOUNT,SOURCE,DESC,CATEGORY
                TXN,%s,111222333,2025-10-01,DEPOSIT,1000.00,Employer,Paycheck,INCOME
                TXN,%s,111222333,2025-10-02,DEBIT,85.49,Costco,Grocery run,GROCERIES
                TXN,%s,111222333,2025-10-03,DEBIT,60.00,Xfinity,Internet bill,UTILITIES
                TXN,%s,111222333,2025-10-05,DEBIT,24.50,AMC,Movie night,ENTERTAINMENT
                """.formatted(userId, userId, userId, userId, userId);

        Files.writeString(bankPath(), content);
    }

    /**
     * Create sample CREDIT DB for the current user (writes to
     * resources/credit_db.txt).
     */
    public static void writeSampleCreditDb(String userId) throws IOException {
        String content = """
                # ==== CREDIT_DB ====
                # Header: USER_ID,CARD_NAME,CARD_NUMBER,CREDIT_LIMIT,CLOSING_DATE,INTEREST_RATE
                CARD,%s,Chase Freedom,5555-6666-7777-8888,5000.00,20,0.30

                # Purchases: USER_ID,CARD_NUMBER,DATE,AMOUNT,MERCHANT,DESC,CATEGORY
                PURCHASE,%s,5555-6666-7777-8888,2025-10-04,52.35,Target,House items,SHOPPING
                PURCHASE,%s,5555-6666-7777-8888,2025-10-06,18.75,Chipotle,Lunch,DINING
                """.formatted(userId, userId, userId);

        Files.writeString(creditPath(), content);
    }

    /**
     * Load both DB files from resources/ into the given store (no changes needed
     * elsewhere).
     */
    public static void loadAllInto(DataStorage store) throws IOException {
        if (Files.exists(bankPath())) {
            loadBank(store, bankPath());
        }
        if (Files.exists(creditPath())) {
            loadCredit(store, creditPath());
        }
    }

    private static void loadBank(DataStorage store, Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Map<String, BankAccount> tempAccounts = new HashMap<>();
        Map<String, String> accountUserMap = new HashMap<>();

        for (String raw : lines) {
            String line = raw.strip();
            if (line.isEmpty() || line.startsWith("#"))
                continue;
            String[] parts = line.split(",", -1);

            if (parts[0].equalsIgnoreCase("ACCOUNT")) {
                // ACCOUNT,USER_ID,ACCOUNT_NUM,ACCOUNT_PIN,BALANCE
                String userId = parts[1];
                String acctNum = parts[2];
                String pin = parts[3];
                double balance = Double.parseDouble(parts[4]);
                BankAccount acct = new BankAccount(balance, acctNum, pin);
                tempAccounts.put(acctNum, acct);
                accountUserMap.put(acctNum, userId);
            } else if (parts[0].equalsIgnoreCase("TXN")) {
                // TXN,USER_ID,ACCOUNT_NUM,DATE,TYPE,AMOUNT,SOURCE,DESC,CATEGORY
                String userId = parts[1];
                String acctNum = parts[2];
                String date = parts[3];
                String type = parts[4];
                double amount = Double.parseDouble(parts[5]);
                String source = parts[6];
                String desc = parts[7];
                String cat = (parts.length > 8 ? parts[8] : "OTHER");

                Transaction t = new Transaction(type, amount, source, desc, date);
                BankAccount acct = tempAccounts.get(acctNum);
                if (acct != null) {
                    acct.addTransaction(t);
                }
            }
        }

        // Group into store by userId
        for (Map.Entry<String, BankAccount> e : tempAccounts.entrySet()) {
            String acctNum = e.getKey();
            BankAccount acct = e.getValue();
            String userId = accountUserMap.get(acctNum);
            store.addBankAccount(userId, acct);
        }
    }

    private static void loadCredit(DataStorage store, Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Map<String, CreditCard> tempCards = new HashMap<>();
        Map<String, String> cardUserMap = new HashMap<>();

        for (String raw : lines) {
            String line = raw.strip();
            if (line.isEmpty() || line.startsWith("#"))
                continue;
            String[] parts = line.split(",", -1);

            if (parts[0].equalsIgnoreCase("CARD")) {
                // CARD,USER_ID,CARD_NAME,CARD_NUMBER,CREDIT_LIMIT,CLOSING_DATE,INTEREST_RATE
                String userId = parts[1];
                String name = parts[2];
                String number = parts[3];
                double limit = Double.parseDouble(parts[4]);
                int closing = Integer.parseInt(parts[5]);
                double apr = Double.parseDouble(parts[6]);

                CreditCard card = new CreditCard(name, number);
                card.setStatementBalance(0.0);
                // (Optional) add setters for limit/closing/apr if you want to reflect file
                // values
                tempCards.put(number, card);
                cardUserMap.put(number, userId);
            } else if (parts[0].equalsIgnoreCase("PURCHASE")) {
                // PURCHASE,USER_ID,CARD_NUMBER,DATE,AMOUNT,MERCHANT,DESC,CATEGORY
                String userId = parts[1];
                String number = parts[2];
                String date = parts[3];
                double amount = Double.parseDouble(parts[4]);
                String merch = parts[5];
                String desc = parts[6];
                CreditCard card = tempCards.get(number);
                if (card != null) {
                    card.makingAPurchase(merch + " - " + desc + " (" + date + ")", amount);
                }
            }
        }

        for (Map.Entry<String, CreditCard> e : tempCards.entrySet()) {
            String cardNum = e.getKey();
            CreditCard card = e.getValue();
            String userId = cardUserMap.get(cardNum);
            store.addCreditCard(userId, card);
        }
    }
}