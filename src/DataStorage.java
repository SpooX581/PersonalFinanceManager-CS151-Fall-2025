package src;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class DataStorage {
    HashMap<String, User> user;

    public String ID() {
        UUID ID = UUID.randomUUID();
        return "user".concat(ID.toString());
    }

    public User getUser(String ID) {
        return check( (() -> {
            return user.get(ID);
        }), ID);
    }

    public String create(String fullName, String username, String password) {
        String ID = ID();
        // generate IDs until new
        while (user.containsKey(ID)) {
            ID = ID();
        }

        User member = new User(ID, fullName, username);
        System.out.println("Welcome, " + fullName);
        user.put(member.getID(), member);
        password(ID, password);
        return ID;
    }

    private User check (Callable<User> callable, String ID) throws Invalid{
        try {
            if(!user.containsKey(ID)) {
                throw new Invalid("ID not in storage", new Throwable());
            }
            return callable.call();
        } catch (Exception e) {
            System.out.println("Cannot complete task: " + e.getMessage());
            return null;
        }

    }

    public void password(String ID, String password) {
        User member = user.get(ID);
        member.setPassword(hash(password));
    }

    public boolean login(String username, String password) {
        User user = username(username);
        if (user != null) {
            return (user.getPassword().contentEquals(hash(password)));
        }

        System.out.println("User does not exist.");
        return false;
    }

    public User username(String username) {
        for (User user : user.values()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private String hash (String password) {
        MessageDigest m = null;
        BigInteger b = BigInteger.ZERO;

        try {
            m = MessageDigest.getInstance("SHA-256");
            b = new BigInteger(1, m.digest(password.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An error occurred while hashing path.");
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("An error occurred while dereferencing path.");
        }

        // message to hex
        StringBuilder hex = new StringBuilder(b.toString(16));

        // zero extend
        while (hex.length() < 64) {
            hex.insert(0, '0');
        }
        return hex.toString();
    }
}