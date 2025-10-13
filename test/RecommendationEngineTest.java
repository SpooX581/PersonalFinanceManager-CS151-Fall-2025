// RecommendationEngineTest.java
// JUnit 4 + Mockito

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecommendationEngineTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @Before
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    // Helpers
    private Transaction tx(String type, double amt) {
        // type = "PURCHASE" or "DEBIT" should count toward spend
        return new Transaction(type, amt, "SRC", "DESC", "2025-10-01");
    }

    private CreditCard mockCard(String name, double cashBack, double stmt, double limit) {
        CreditCard cc = mock(CreditCard.class);
        when(cc.getCreditCardName()).thenReturn(name);
        when(cc.getCashBack()).thenReturn(cashBack);
        when(cc.getStatementBalance()).thenReturn(stmt);
        when(cc.getCreditLimit()).thenReturn(limit);
        return cc;
    }

    private String output() {
        return out.toString();
    }

    // spendingAlerts()
    @Test
    public void spendingAlerts_noBudget_printsNotice() {
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 50), tx("DEBIT", 25));
        RecommendationEngine re = new RecommendationEngine(txs, 0.0, Collections.emptyList(), Collections.emptyList());

        re.spendingAlerts();
        String s = output();

        // Your class prints "n=== Spending vs budget ===" (likely a typo). We assert on
        // the stable substring:
        assertTrue(s.contains("Spending vs budget"));
        assertTrue(s.contains("Month-to-date spend: $75.00 | Budget $0.00"));
        assertTrue(s.contains("No monthly budget set"));
    }

    @Test
    public void spendingAlerts_under80_printsOnTrack() {
        // spent = 40, budget = 100 -> 0.40
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 20), tx("DEBIT", 20));
        RecommendationEngine re = new RecommendationEngine(txs, 100.0, Collections.emptyList(),
                Collections.emptyList());

        re.spendingAlerts();
        String s = output();

        assertTrue(s.contains("Month-to-date spend: $40.00 | Budget $100.00"));
        assertTrue(s.contains("Great job, you're on track!"));
    }

    @Test
    public void spendingAlerts_80to100_printsHeadsUp() {
        // spent = 90, budget = 100 -> 0.90
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 50), tx("DEBIT", 40));
        RecommendationEngine re = new RecommendationEngine(txs, 100.0, Collections.emptyList(),
                Collections.emptyList());

        re.spendingAlerts();
        String s = output();

        assertTrue(s.contains("Month-to-date spend: $90.00 | Budget $100.00"));
        assertTrue(s.contains("passed 80% of your budget"));
    }

    @Test
    public void spendingAlerts_100to110_printsDanger() {
        // spent = 105, budget = 100 -> 1.05
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 55), tx("DEBIT", 50));
        RecommendationEngine re = new RecommendationEngine(txs, 100.0, Collections.emptyList(),
                Collections.emptyList());

        re.spendingAlerts();
        String s = output();

        assertTrue(s.contains("Month-to-date spend: $105.00 | Budget $100.00"));
        assertTrue(s.contains("Danger. You are over budget"));
    }

    @Test
    public void spendingAlerts_over110_printsWarningFreeze() {
        // spent = 120, budget = 100 -> 1.20
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 60), tx("DEBIT", 60));
        RecommendationEngine re = new RecommendationEngine(txs, 100.0, Collections.emptyList(),
                Collections.emptyList());

        re.spendingAlerts();
        String s = output();

        assertTrue(s.contains("Month-to-date spend: $120.00 | Budget $100.00"));
        assertTrue(s.contains("~10%+ over budget"));
    }

    // cashbackSummary()
    @Test
    public void cashbackSummary_noCards_printsNotice() {
        RecommendationEngine re = new RecommendationEngine(Collections.emptyList(), 500.0,
                Collections.emptyList(), Collections.emptyList());

        re.cashbackSummary();
        String s = output();

        assertTrue(s.contains("=== Cashback Summary ==="));
        assertTrue(s.contains("No credit cards on file."));
    }

    @Test
    public void cashbackSummary_withCards_sumsAndPrints() {
        CreditCard a = mockCard("Chase Sapphire", 12.34, 200.00, 5000.00);
        CreditCard b = mockCard("Amex Blue", 5.00, 150.00, 3000.00);

        RecommendationEngine re = new RecommendationEngine(Collections.emptyList(), 500.0,
                Arrays.asList(a, b), Collections.emptyList());

        re.cashbackSummary();
        String s = output();

        assertTrue(
                s.contains("Chase Sapphire: Cashback this month $12.34 (Statement Balance $200.00, Limit $5000.00)"));
        assertTrue(s.contains("Amex Blue: Cashback this month $5.00 (Statement Balance $150.00, Limit $3000.00)"));
        assertTrue(s.contains("Total cashback across cards: $17.34"));
    }

    // billsFocusPlan()
    @Test
    public void billsFocusPlan_noBills_printsNotice() {
        RecommendationEngine re = new RecommendationEngine(Collections.emptyList(), 500.0,
                Collections.emptyList(), Collections.emptyList());

        re.billsFocusPlan();
        String s = output();

        assertTrue(s.contains("=== Bills to Prioritize ==="));
        assertTrue(s.contains("No bills on file"));
    }

    @Test
    public void billsFocusPlan_sortsByDueDayUnknownLast_andPrintsTotals() {
        List<RecommendationEngine.Bill> bills = Arrays.asList(
                new RecommendationEngine.Bill("Electric", 90, 10),
                new RecommendationEngine.Bill("Internet", 60, 5),
                new RecommendationEngine.Bill("Rent", 1200, 1),
                new RecommendationEngine.Bill("Gym", 35, null) // unknown due
        );

        RecommendationEngine re = new RecommendationEngine(Collections.emptyList(), 500.0,
                Collections.emptyList(), bills);

        re.billsFocusPlan();
        String s = output();

        // total
        assertTrue(s.contains("Total upcoming bills: $1,385.00") || s.contains("Total upcoming bills: $1385.00"));

        // order: due 1, 5, 10, unknown
        int iRent = s.indexOf("Rent");
        int iInternet = s.indexOf("Internet");
        int iElectric = s.indexOf("Electric");
        int iGym = s.indexOf("Gym");

        assertTrue(iRent != -1 && iInternet != -1 && iElectric != -1 && iGym != -1);
        assertTrue(iRent < iInternet && iInternet < iElectric && iElectric < iGym);

        // includes suggestion lines
        assertTrue(s.contains("Suggested allocation strategy"));
        assertTrue(s.contains("Cover all essentials"));
    }

    // runAll() wrapper
    @Test
    public void runAll_callsAllSectionsAndEnds() {
        List<Transaction> txs = Arrays.asList(tx("PURCHASE", 1));
        List<CreditCard> cards = Arrays.asList(mockCard("Card", 1.0, 10.0, 100.0));
        List<RecommendationEngine.Bill> bills = Arrays.asList(new RecommendationEngine.Bill("Phone", 45, 15));

        RecommendationEngine re = new RecommendationEngine(txs, 100, cards, bills);

        re.runAll();
        String s = output();

        assertTrue(s.contains("Spending vs budget"));
        assertTrue(s.contains("=== Cashback Summary ==="));
        assertTrue(s.contains("=== Bills to Prioritize ==="));
        assertTrue(s.contains("(End of recommendations)"));
    }
}
