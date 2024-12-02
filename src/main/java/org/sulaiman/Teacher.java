package org.sulaiman;

public class Teacher extends User{
    private int tacherId;
    private String name;

    public Teacher(String username, String password, boolean role, int tacherId, String name) {
        super(username, password, role);
        this.tacherId = tacherId;
        this.name = name;
    }

    public int getTacherId() {
        return tacherId;
    }

    public void setTacherId(int tacherId) {
        this.tacherId = tacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
