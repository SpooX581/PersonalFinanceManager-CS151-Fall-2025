import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    @Test
    public void testGetters() {
        BankAccount test = new BankAccount(500, "17172", "192112");
        // Balance
        assertEquals(500, test.getBalance(), 0.001);
        // AccountNum
        assertEquals("17172", test.getAccountNum());
        // AccountPin
        assertEquals("192112", test.getAccountPin());
    }

    @Test
    public void testRequest() {
        BankAccount test = new BankAccount(500, "17172", "192112");

        // Positive
        test.request(200);
        assertEquals(500, test.getBalance(), 0.001);

        // Nonpositive
        test.request(0);
        assertEquals(500, test.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        BankAccount withdrawLess = new BankAccount(500, "17173", "192138");
        BankAccount withdrawEqual = new BankAccount(500, "17174", "9235412");
        BankAccount withdrawGreater = new BankAccount(500, "17175", "853212");

        // Amount less than balance
        withdrawLess.withdraw(300);
        assertEquals(200, withdrawLess.getBalance(), 0.001);

        // Amount equal to balance
        withdrawEqual.withdraw(500);
        assertEquals(0, withdrawEqual.getBalance(), 0.001);

        // Amount greater than balance
        withdrawGreater.withdraw(600);
        assertEquals(500, withdrawGreater.getBalance(), 0.001);
    }

    @Test
    public void testDeposit() {
        BankAccount depositPositive = new BankAccount(500, "171233", "19322138");
        BankAccount depositNonPositive = new BankAccount(500, "1723474", "92335412");

        // Amount not positive
        depositNonPositive.deposit(0);
        assertEquals(500, depositNonPositive.getBalance(), 0.001);

        // Amount positive
        depositPositive.deposit(60);
        assertEquals(560, depositPositive.getBalance(), 0.001);
    }

    @Test
    public void testTransfer() {
        BankAccount transferNonPositive = new BankAccount(500, "173254173", "1932138");
        BankAccount transferPositiveLessThanEqualBalance = new BankAccount(500, "145346374", "923545212");
        BankAccount transferPositiveGreaterThan = new BankAccount(500, "17232175", "853325212");

        // Transfer non positive
        transferNonPositive.transfer(0);
        assertEquals(500, transferNonPositive.getBalance(), 0.001);

        // Transfer positive number less than/equal to balance
        transferPositiveLessThanEqualBalance.transfer(300);
        assertEquals(200, transferPositiveLessThanEqualBalance.getBalance(), 0.001);

        // Transfer positive number greater than balance
        transferPositiveGreaterThan.transfer(600);
        assertEquals(500, transferPositiveGreaterThan.getBalance(), 0.001);
    }
}
