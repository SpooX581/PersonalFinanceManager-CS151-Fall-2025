public class BudgetPlannerTest {
    public static void main(String[] args) {
        //Below budget
        BudgetPlanner belowBudget = new BudgetPlanner(500,400);
        planner1.manageBudget();
        System.out.println();

        //Exact budget
        BudgetPlanner exactBudget = new BudgetPlanner(800,800);
        planner2.manageBudget();
        System.out.println();

        //Over budget
        BudgetPlanner overBudget = new BudgetPlanner(800,1200);
        planner3.manageBudget();
    }

    
}
