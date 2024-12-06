package org.sulaiman;

public class Grade implements FromDataBase {
    private int gradeId;
    private final Student student;
    private final Subject subject;
    private double gradeNumber;

    public Grade(int uid, Student student, Subject subject, double gradeNumber) {
        this.gradeId = uid;
        this.student = student;
        this.subject = subject;
        this.gradeNumber = gradeNumber;
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
    public int getUid() {
        return this.gradeId;
    }
}
