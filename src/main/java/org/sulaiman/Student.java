package org.sulaiman;

import com.mysql.cj.x.protobuf.MysqlxSession;

import java.util.ArrayList;

public class Student extends AbstractUser {
    private ArrayList<Subject> subjects;

    public Student (int studentId, String firstName, String lastName, String userName,String password , String email) {
        super(studentId, firstName,lastName,userName, password,email, false);
        this.subjects = new ArrayList<>();
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
    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public ArrayList<Grade> getGrades()  {
        ArrayList<Grade> grades = new ArrayList<>();

        for (Subject subject : subjects) {
            for (Grade grade : subject.getGrades()) {
                if(grade.getStudent() == this) {
                    grades.add(grade);
                }
            }
        }

        return grades;
    }
}
