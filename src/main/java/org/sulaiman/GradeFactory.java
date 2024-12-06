package org.sulaiman;

public class GradeFactory {
    public static Grade createGrade(Subject subject, Student student, int score){
        return new Grade(student, subject, score);
    }
}
