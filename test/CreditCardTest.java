import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCardTest {
    @Test
    public void testGettersAndSetters() {
        CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);

        testCard.setCreditCardName("Bank of America");
        testCard.setCreditCardNumber("1000 0000 0000 0000");
        testCard.setStatementBalance(1500.0);

        assertEquals("Bank of America", testCard.getCreditCardName());
        assertEquals("1000 0000 0000 0000", testCard.setCreditCardNumber());
        assertEquals(1500.0, testCard.setStatementBalance(), 0.001);

         
    }

    @Test 
    public void testmakingAPurchase() {
       CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
       testCard.makingAPurchase("Shoes", 200.0);
       assertEquals(200.0, testCard.getStatementBalance(), 0.001);
       assertEquals(testCard.getCashBack() > 0.0);
    }

    @Test
    public void testCreditCardPayment() {
        CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
       testCard.creditCardPayment(200.0);
        assertEquals(200.0, testCard.getStatementBalance(), 0.001);
    }

    @Test
    public void testseePurchases() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
        testCard.makingAPurchase("item1", 100.0);
        testCard.makingAPurchase("item1",400.0);
        testCard.makingAPurchase("item1", 25.00);

        assertEquals(3, testCard.getPurchases().size());
        assertEquals(525.00, testCard.getStatementBalance(), 0.001);
    }

    @Test
    public void testseeClosingDate() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);  
    assertEquals(30, testCard.getClosingDate());  
    }

    @Test
    public void testseeCcashBack() {
    CreditCard testCard = new CreditCard("Chase", "1302 4957 2939 5762", 1000.0, 20, 0.0, 0.0, 0, 0, 0.0);
    testCard.makingAPurchase(100.0);
    testCard.calculateCashBack();
    assertEquals(1.00, testCard.getCashBack(), 0.001);
    }
}
