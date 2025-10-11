public class User {
    private String fullName;
    private String username;
    private String password;
    private String dateOfBirth;
    private double networth;
    private int creditCards;
    private int bankAccounts;

    public User (String fullName, String username, String password, String dateOfBirth, double networth, int creditCards, int bankAccounts) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.networth = networth;
        this.creditCards = creditCards;
        this.bankAccounts = bankAccounts;
    }
    // getters
    public String getFullName(String fullName) {
        return fullName;
    }
    public String getUsername(String username) {
        return username;
    }
    public String getPassword(String password) {
        return password;
    }
    public String getDateOfBirth(String dateOfBirth) {
        return dateOfBirth;
    }
    public double getNetworth(double networth) {
        return networth;
    }
    public int getCreditCards(int creditCards) {
        return creditCards;
    }
    public int getBankAccounts(int bankAccounts) {
        return bankAccounts;
    }
    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNetworth(double networth) {
        this.networth = networth;
    }
    public void setCreditCards(int creditCards) {
        this.creditCards = creditCards;
    }
    public void setBankAccounts(int bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

}