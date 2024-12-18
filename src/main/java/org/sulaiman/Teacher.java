package org.sulaiman;

import java.util.ArrayList;

public class Teacher extends AbstractUser {
    private ArrayList<Subject> subject;
    private ArrayList<ClassRoom> classRoom;


    public Teacher(int teacherId, String firstName, String lastName, String userName, String password, String email) {
        super(teacherId, firstName,lastName,userName, password,email, true);
        this.subject = new ArrayList<Subject>();
        this.classRoom = new ArrayList<ClassRoom>();
    }

    public void removeSubject(Subject subject) {
        this.subject.remove(subject);
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

}
