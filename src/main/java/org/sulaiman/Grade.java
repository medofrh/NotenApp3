package org.sulaiman;

public class Grade implements FromDataBase{
    private int gradeId;
    private Student student;
    private Subject subject;

    private double gradeNumber;

    public Grade(int gradeId, Student student, Subject subject, double gradeNumber) {
        this.gradeId = gradeId;
        this.student = student;
        this.subject = subject;
        this.gradeNumber = gradeNumber;

        if(subject != null){
            subject.addGrade(this);
        }

    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(double gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", student=" + student +
                ", subject=" + subject +
                ", gradeNumber=" + gradeNumber +
                '}';
    }
}
