package org.sulaiman;

import java.util.ArrayList;

public class Teacher extends AbstractUser implements FromDataBase{
    private int tacherId;
    private String firsteName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private boolean role;
    private ArrayList<Subject> subject;
    private ArrayList<ClassRoom> classRoom;


    public Teacher(int tacherId, String firstName, String lastName, String userName,String password , String email, boolean role, Subject subject,ClassRoom classRoom) {
        super(tacherId, firstName,lastName,userName, password,email, role);
        this.subject = new ArrayList<Subject>();
        this.classRoom = new ArrayList<ClassRoom>();
        this.tacherId = tacherId;
        this.firsteName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.subject.add(subject);
        this.classRoom.add(classRoom);
    }

    public int getTacherId() {
        return tacherId;
    }

    public String getFirstName() {
        return firsteName;
    }

    public void setFirstName(String name) {
        this.firsteName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getUserName() {
        return userName;
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

    public void addSubject(Subject subject) {
            this.subject.add(subject);
    }

    public ArrayList<Subject> getSubject() {
        return this.subject;
    }

    public void addClassRoom(ClassRoom classRoom) {
        this.classRoom.add(classRoom);
    }

    public ArrayList<ClassRoom> getClassRoom() {
        return this.classRoom;
    }
    public void addSubGrade(Subject subject,Student student, Grade grade){
        //TODO Hier braucehn wir factory mthode pattern, weil class Grade muss dann erzeugt werden wenn diese Methode aufgerufen wird
        subject.setGrade(student,grade);
    }

    public String toString() {
        return "Teacher{" +
                "tacherId=" + tacherId +
                ", firsteName='" + firsteName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", subject=" + subject +
                ", classRoom=" + classRoom +
                '}';
    }
}
