package org.sulaiman;

public class Grade implements FromDataBase{
    private int gradeId;
    private static int counter= 0;
    private Student student;
    private int subjectId;

    private double gradeNumber;

    public Grade(Student student, Subject subject, double gradeNumber) {
        counter++;
        this.gradeId = counter;
        this.student = student;
        this.subjectId = subject.getSubjectId();
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subject) {
        this.subjectId = subject;
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
                ", subject=" + subjectId +
                ", gradeNumber=" + gradeNumber +
                '}';
    }
}
