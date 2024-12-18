package org.sulaiman;

import com.mysql.cj.x.protobuf.MysqlxSession;

import java.util.ArrayList;

public class Student extends AbstractUser {
    private ArrayList<Subject> subjects;

    public Student (int studentId, String firstName, String lastName, String userName,String password , String email) {
        super(studentId, firstName,lastName,userName, password,email, false);
        this.subjects = new ArrayList<>();
    }
    
    public void addSubject(Subject subject) {
        if(this.subjects.contains(subject)) return;

        this.subjects.add(subject);
        subject.addStudent(this);
    }
    public void removeSubject(Subject subject) {
        if(!this.subjects.contains(subject)) return;

        this.subjects.remove(subject);
        subject.removeStudent(this);
    }
}
