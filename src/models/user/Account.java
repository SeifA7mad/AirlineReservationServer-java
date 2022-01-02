package models.user;

import java.io.Serializable;

public class Account implements Serializable {
    private int accountID;
    private String username;
    private String email;
    private String password;
    private String accountType;
    private User user;

    public boolean login(String email, String password) {
        if (this.email.contains(email) && this.password.contains(password)) {
            return true;
        } else {
            return false;
        }
    }

    public Account createAccount(String username, String email, String password, String accType, User user) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accType;
        this.user = user;

        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public User getUser() {
        return user;
    }
}
