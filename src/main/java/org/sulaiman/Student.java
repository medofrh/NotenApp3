package org.sulaiman;

import java.util.ArrayList;

public class Student extends AbstractUser implements FromDataBase{
    private ClassRoom classRoom;

    public Student (int studentId, String firstName, String lastName, String userName,String password , String email, ClassRoom classRoom) {
        super(studentId, firstName,lastName,userName, password,email, false);
        this.classRoom = classRoom;
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
                "studentId=" + super.getUserId() +
                ", firsteName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", userName='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }
}
