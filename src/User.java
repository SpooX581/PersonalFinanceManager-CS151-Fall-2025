import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class User {
    private String fullName = "default fullName";
    private String username = "default username";
    private String password = "default password";
    private LocalDate dateOfBirth = LocalDate.of(1500, 1, 1);
    private double networth = 0.0;
    private int creditCards = 0;
    private int bankAccounts = 0;

    public User() {
    }

    public User(String fullName, String username, String password, LocalDate dateOfBirth, double networth,
            int creditCards, int bankAccounts) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.networth = networth;
        this.creditCards = creditCards;
        this.bankAccounts = bankAccounts;
    }

    public void printUserInfo() {
        System.out.println("Full Name: " + fullName);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Networth: " + networth);
        System.out.println("Credit Cards: " + creditCards);
        System.out.println("Bank Accounts: " + bankAccounts);
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

    public LocalDate getDateOfBirth(LocalDate dateOfBirth) {
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
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public void createUser(String DOB, String fullName, String username, String password) {
        try {
            this.fullName = fullName;
            this.username = username;
            this.password = password;
            this.dateOfBirth = LocalDate.parse(DOB);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        System.out.println("Welcome, " + fullName + "! Your username is " + username);
    }
}