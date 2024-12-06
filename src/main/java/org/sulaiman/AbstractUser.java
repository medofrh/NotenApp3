package org.sulaiman;

abstract public class AbstractUser implements FromDataBase {
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

    public abstract boolean login();

    public abstract boolean logout();

    public abstract void changePassword();

    public abstract void updateUserData();

    public abstract void deleteAccount();

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

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
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

    public int getUid() {
        return this.userId;
    }

    public boolean isTeacher() {
        return this.role;
    }
}
