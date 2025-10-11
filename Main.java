import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Personal Finance Manager! Let's get you started!");
        System.out.println("Please enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Please create a username: ");
        String username = scanner.nextLine();
        System.out.println("Please create a password: ");
        String password = scanner.nextLine();
        System.out.println("Please enter your date of birth in MM/DD/YYYY format: ");
        String dateOfBirth = scanner.nextLine();


    }
}
