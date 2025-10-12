import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AuthService {
    private static final String USERS_FILE = "users.txt";

    public static class AuthResult {
        public final boolean success;
        public final User user;
        public final String message;

        public AuthResult(boolean success, User user, String message) {
            this.success = success;
            this.user = user;
            this.message = message;
        }
    }

    // USERS FILE format (TXT):
    // USER_ID,USERNAME,PASSWORD,FULL_NAME,DOB(YYYY-MM-DD)
    public static void ensureUsersFile() throws IOException {
        if (!Files.exists(Paths.get(USERS_FILE))) {
            Files.writeString(Paths.get(USERS_FILE), "# USER_ID,USERNAME,PASSWORD,FULL_NAME,DOB\n");
        }
    }

    public static AuthResult register(String fullName, String username, String password, String dob) {
        try {
            ensureUsersFile();
            // reject if username exists
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            for (String l : lines) {
                if (l.startsWith("#") || l.isBlank())
                    continue;
                String[] p = l.split(",", -1);
                if (p.length >= 3 && p[1].equals(username)) {
                    return new AuthResult(false, null, "Username already exists.");
                }
            }
            String userId = java.util.UUID.randomUUID().toString();
            String row = "%s,%s,%s,%s,%s%n".formatted(userId, username, password, fullName, dob);
            Files.writeString(Paths.get(USERS_FILE), row, java.nio.file.StandardOpenOption.APPEND);

            User u = new User();
            u.setUserId(userId);
            u.createUser(dob, fullName, username, password);
            return new AuthResult(true, u, "Registered!");
        } catch (Exception e) {
            return new AuthResult(false, null, "Register error: " + e.getMessage());
        }
    }

    public static AuthResult login(String username, String password) {
        try {
            ensureUsersFile();
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            for (String l : lines) {
                if (l.startsWith("#") || l.isBlank())
                    continue;
                String[] p = l.split(",", -1);
                if (p.length >= 5) {
                    String uid = p[0], un = p[1], pw = p[2], name = p[3], dob = p[4];
                    if (un.equals(username) && pw.equals(password)) {
                        User u = new User();
                        u.setUserId(uid);
                        u.createUser(dob, name, un, pw);
                        return new AuthResult(true, u, "Login successful.");
                    }
                }
            }
            return new AuthResult(false, null, "Invalid username or password.");
        } catch (Exception e) {
            return new AuthResult(false, null, "Login error: " + e.getMessage());
        }
    }
}
