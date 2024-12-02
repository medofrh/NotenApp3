package org.sulaiman;

import java.util.ArrayList;

public class Student extends AbstractUser implements FromDataBase{
    private int studentId;
    private String firsteName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private ClassRoom classRoom;

    public Student (int studentId, String firstName, String lastName, String userName,String password , String email, ClassRoom classRoom) {
        super(studentId, firstName,lastName,userName, password,email, false);
        this.studentId = studentId;
        this.firsteName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.classRoom = classRoom;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public ArrayList<Subject> getSubject(ClassRoom classRoom) {
       return  this.classRoom.getSubjects();
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public ArrayList<Grade> getGrade(Subject subj){
        return subj.getGrade();
    }
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firsteName='" + firsteName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }
}
