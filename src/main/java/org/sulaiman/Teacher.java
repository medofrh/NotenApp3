package org.sulaiman;

import java.util.ArrayList;

public class Teacher extends AbstractUser implements FromDataBase{
    private ArrayList<Subject> subject;
    private ArrayList<ClassRoom> classRoom;


    public Teacher(int teacherId, String firstName, String lastName, String userName, String password, String email, Subject subject,ClassRoom classRoom) {
        super(teacherId, firstName,lastName,userName, password,email, true);
        this.subject = new ArrayList<Subject>();
        this.classRoom = new ArrayList<ClassRoom>();
        this.subject.add(subject);
        this.classRoom.add(classRoom);
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
        subject.addGrade(grade);
    }

    public String toString() {
        return "Teacher{" +
                "teacherId=" + super.getUserId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", userName='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", subject=" + subject +
                ", classRoom=" + classRoom +
                '}';
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void changePassword() {

    }

    @Override
    public void updateUserData() {

    }

    @Override
    public void deleteAccount() {

    }
}
