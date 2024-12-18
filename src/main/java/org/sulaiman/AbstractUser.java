package org.sulaiman;

abstract public class AbstractUser implements FromDataBase {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean role;

    public AbstractUser(int userId, String firstName, String lastName, String username, String password, String email, boolean role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getUid() {
        return this.userId;
    }

    public boolean isTeacher() {
        return this.role;
    }
}
