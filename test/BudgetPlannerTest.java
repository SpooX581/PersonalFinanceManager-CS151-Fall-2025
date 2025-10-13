import org.junit.Test;
import static org.unit.Assert.*;

public class BudgetPlannerTest {

    @Test
    public void testBelowBudget() {
        //Below budget
        BudgetPlanner belowBudget = new BudgetPlanner(500, 400);
        assertTrue(belowBudget.getSpending() < belowBudget.getBudget());
    }
    @Test
    public void testExactBudget() {
        //Exact budget
        BudgetPlanner exactBudget = new BudgetPlanner(800, 800);
        assertEquals(exactBudget.getBudget(), exactBudget.getSpending(), 0.001);
    }
    @Test
    public void testOverBudget() {
        //Over budget
        BudgetPlanner overBudget = new BudgetPlanner(800, 1200);
        assertTrue(overBudget.getSpending() > overBudget.getBudget());
    }
}
