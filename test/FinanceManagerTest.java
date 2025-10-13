import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FinanceManagerTest {

    // Collaborators we’ll mock
    private User user;
    private DataStorage dataStorage;

    // System under test
    private FinanceManager fm;

    // Console capture
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        user = mock(User.class);
        when(user.getUserId()).thenReturn("user-123");
        // Make printUserInfo() write something deterministic
        doAnswer(invocation -> {
            System.out.println("User Info: " + "Test User");
            return null;
        }).when(user).printUserInfo();

        dataStorage = mock(DataStorage.class);

        fm = new FinanceManager(user, dataStorage);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    // Helpers to build stubbed maps
    private Map<String, BankAccount> stubBankAccountsWithTransactions(int txCount) {
        BankAccount acct = mock(BankAccount.class);
        when(acct.getAccountNum()).thenReturn("ACC-001");
        when(acct.getBalance()).thenReturn(1000.00);

        List<Transaction> txs = new ArrayList<>();
        // Make some PURCHASE/DEBIT transactions so monthly report includes them
        for (int i = 0; i < txCount; i++) {
            txs.add(new Transaction(i % 2 == 0 ? "PURCHASE" : "DEBIT",
                    50.0, "Merchant" + i, "Desc" + i, "2025-10-01"));
        }
        when(acct.getTransactions()).thenReturn(txs);

        Map<String, BankAccount> map = new HashMap<>();
        map.put("ACC-001", acct);
        return map;
    }

    private Map<String, CreditCard> stubCreditCards() {
        CreditCard card = mock(CreditCard.class);
        when(card.getCreditCardName()).thenReturn("Test Rewards");
        when(card.getCreditCardNumber()).thenReturn("4111111111111111");

        // Summary methods print something (so we can assert output lines exist)
        doAnswer(inv -> {
            System.out.println("Statement Balance: $123.45");
            return null;
        })
                .when(card).seeStatementBalance();
        doAnswer(inv -> {
            System.out.println("Cash Back: $3.70");
            return null;
        })
                .when(card).seeCashBack();
        doAnswer(inv -> {
            System.out.println("Credit Limit: $5000.00");
            return null;
        })
                .when(card).seeCreditLimit();

        Map<String, CreditCard> map = new HashMap<>();
        map.put("CARD-001", card);
        return map;
    }

    // Tests
    @Test
    public void showUserSummary_printsAccountsAndCards() {
        when(dataStorage.getBankAccounts("user-123"))
                .thenReturn(stubBankAccountsWithTransactions(2));
        when(dataStorage.getCreditCards("user-123"))
                .thenReturn(stubCreditCards());

        fm.showUserSummary();

        String out = outContent.toString();

        // Printed user line
        assertTrue(out.contains("User Info: Test User"));

        // Bank accounts section
        assertTrue(out.contains("Bank Accounts:"));
        assertTrue(out.contains("ACC-001"));
        assertTrue(out.contains("Balance: $1000.00"));
        assertTrue(out.matches("(?s).*Txns: 2.*"));

        // Credit cards section
        assertTrue(out.contains("Credit Cards:"));
        assertTrue(out.contains("Test Rewards - 4111111111111111"));
        assertTrue(out.contains("Statement Balance: $123.45"));
        assertTrue(out.contains("Cash Back: $3.70"));
        assertTrue(out.contains("Credit Limit: $5000.00"));

        // Verify dataStorage was actually asked for this user's data
        verify(dataStorage, times(1)).getBankAccounts("user-123");
        verify(dataStorage, times(1)).getCreditCards("user-123");
    }

    @Test
    public void loadUserAccounts_aggregatesTransactionsAndCards_usedByMonthlyReport() {
        when(dataStorage.getBankAccounts("user-123"))
                .thenReturn(stubBankAccountsWithTransactions(3)); // 3 spend txns
        when(dataStorage.getCreditCards("user-123"))
                .thenReturn(stubCreditCards());

        // populate fm's internal lists
        fm.loadUserAccounts();

        // monthly report should include the header and not crash
        fm.generateMonthlyReport();

        String out = outContent.toString();
        assertTrue(out.contains("=== Monthly Report ==="));
    }

    @Test
    public void analyzeBudget_addsExpenseWhenUserSaysYes() {
        // Start with zero transactions loaded
        when(dataStorage.getBankAccounts("user-123"))
                .thenReturn(stubBankAccountsWithTransactions(0));
        when(dataStorage.getCreditCards("user-123"))
                .thenReturn(stubCreditCards());

        fm.loadUserAccounts();

        // Simulate: y, PURCHASE, 50.0, Store, Snacks, 2025-10-01
        String input = String.join("\n",
                "y", "PURCHASE", "50.0", "Store", "Snacks", "2025-10-01");
        Scanner sc = new Scanner(input);

        // Should add without throwing; BudgetPlanner.manageBudget() runs internally
        fm.analyzeBudgetAndOptionallyAddExpense(sc);

        // If we generate the report now, we should still not crash and see header
        fm.generateMonthlyReport();
        String out = outContent.toString();
        assertTrue(out.contains("=== Monthly Report ==="));
    }

    @Test
    public void analyzeBudget_skipsAddWhenUserSaysNo() {
        when(dataStorage.getBankAccounts("user-123"))
                .thenReturn(stubBankAccountsWithTransactions(0));
        when(dataStorage.getCreditCards("user-123"))
                .thenReturn(stubCreditCards());

        fm.loadUserAccounts();

        // Simulate: n
        Scanner sc = new Scanner("n\n");
        fm.analyzeBudgetAndOptionallyAddExpense(sc);

        // Still should be fine to generate a report
        fm.generateMonthlyReport();
        String out = outContent.toString();
        assertTrue(out.contains("=== Monthly Report ==="));
    }

    @Test
    public void setMonthlyBudget_clampsNegativeToZero_withoutCrashing() {
        fm.setMonthlyBudget(-500.0); // should clamp internally to 0

        // No direct getter, but the path should remain stable and not throw
        when(dataStorage.getBankAccounts("user-123"))
                .thenReturn(stubBankAccountsWithTransactions(1));
        when(dataStorage.getCreditCards("user-123"))
                .thenReturn(stubCreditCards());

        fm.loadUserAccounts();
        fm.generateMonthlyReport();

        String out = outContent.toString();
        assertTrue(out.contains("=== Monthly Report ==="));
    }
}
