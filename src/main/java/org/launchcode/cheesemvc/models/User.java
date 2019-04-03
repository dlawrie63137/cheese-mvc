package org.launchcode.cheesemvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min=5, max=15)
    private String userName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=6, max=20)
    private String password;

    @NotNull(message="Passwords Do Not Match.")
    private String verifyPassword;

    private int userId;
    private static int newUserId=0;

    public User() {
        this.userId=newUserId;
        newUserId++;
    }

    public User(String userName, String email, String password, @NotNull @NotEmpty String verifyPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    private void checkPassword(String password, String verifyPassword) {
        if (!(password==null) && !(verifyPassword==null)) {
            if (!password.equals(verifyPassword)){
                setVerifyPassword(null);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(password, verifyPassword);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword(password, verifyPassword);
    }
}
