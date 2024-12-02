package org.sulaiman;

abstract public class AbstractUser {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean role;
    private DB dbConnection;

    public AbstractUser(int userId, String firstName, String lastName, String username, String password, String email, boolean role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.dbConnection = new DB();
    }

    public boolean login(){return false;}

    public boolean logout(){return false;}

    public void changePassword(String newPassword){}

    public void updateUserData(){}

    public void deleteAccount(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
