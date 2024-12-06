package org.sulaiman;

public class Grade implements FromDataBase{
    private static int counter= 0;
    private final int gradeId;
    private final Student student;
    private final Subject subject;
    private double gradeNumber;

    public Grade(Student student, Subject subject, double gradeNumber) {
        counter++;
        this.gradeId = counter;
        this.student = student;
        this.subject = subject;
        this.gradeNumber = gradeNumber;

        if(subject == null){
            throw new IllegalArgumentException("Subject can't be null");
        }
        subject.addGrade(this);
    }

    public int getGradeId() {
        return gradeId;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(double gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + (student != null ? student.getUserId() : "null") +
                ", subjectName=" + (subject != null ? subject.getName() : "null") +
                ", gradeNumber=" + gradeNumber +
                '}';
    }
}
