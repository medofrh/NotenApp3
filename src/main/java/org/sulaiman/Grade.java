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

    public Student getStudent() {
        return student;
    }

    public double getGradeNumber() {
        return gradeNumber;
    }

    @Override
    public int getUid() {
        return this.gradeId;
    }
}
