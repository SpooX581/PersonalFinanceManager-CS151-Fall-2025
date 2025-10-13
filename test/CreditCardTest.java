import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCardTest {
    @Test
    void testGetters() {
        CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);

        assertEquals("Chase", testCard.getCreditCardName());
        assertEquals("1302 4957 2939 5762", testCard.getCreditCardNumber());
        assertEquals(1000.0, testCard.getStatementBalance());
        assertEquals(20, testCard.getClosingDate());
        assertEquals(0.0, testCard.getCashBack());
        assertEquals(0.0, testCard.getCreditLimit());
        assertEquals(0.0, testCard.getInterestRate());
        assertEquals(0, testCard.getDueDate());
        assertEquals(0.0, testCard.getMinimumPayment());
         
    }
    @Test
    void testSetters() {
        CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);

        testCard.setCreditCardName("Bank of America");
        testCard.setCreditCardNumber("1000 0000 0000 0000");
        testCard.setStatementBalance(1500.0);

        assertEquals("Bank of America", testCard.setCreditCardName());
        assertEquals("1000 0000 0000 0000", testCard.setCreditCardNumber());
        assertEquals(1500.0, testCard.setStatementBalance(), 0.001);
    }

    @Test 
    public void testPurchase() {
       CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
       testCard.makePurchase(200.0);
       assertEquals(200.0, testCard.getStatementBalance(), 0.001);
    }

    @Test
    public void testPayment() {
        CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
       testCard.makePayment(200.0);
        assertEquals(200.0, testCard.getStatementBalance(), 0.001);
    }

    @Test
    public void testPurchaseList() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
        testCard.makePurchase(100.0);
        testCard.makePurchase(400.0);
        testCard.makePurchase(25.00);

        assertEquals(3, testCard.getPurchases().size());
        assertEquals(525.00, testCard.getStatementBalance());
    }

    @Test
    public void testClosingDate() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);  
    assertEquals(30, testCard.getClosingDate());  
    }

    @Test
    public void cashBack() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
    testCard.makePurchase(100.0);
    testCard.calculateCashBack();
    assertEquals(1.00, testCard.getCashBack(), 0.001);
    }
}
