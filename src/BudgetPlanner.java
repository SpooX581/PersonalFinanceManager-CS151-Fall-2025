public class BudgetPlanner {
    private double budget;
    private double spending;

    public BudgetPlanner(double budget, double spending){
        this.budget= budget;
        this.spending = spending;
    }

    public void manageBudget(){
        System.out.println("Your budget is $" +budget+".");
        System.out.println("Your spending amount this month is $" +spending+".");

        if(spending<budget){
            System.out.println("You have stayed within your budget this month. You have $"+(budget-spending)+" left over for yourself.");
        }
        else if(spending==budget){
            System.out.println("You have reached your budget limit this month. You have $0 left over for yourself.");
        }
        else{
            System.out.println("You are over your budget limit this month by $"+(spending-budget)+".");
        }
    }
}
