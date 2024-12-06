package org.sulaiman;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Subject sub1 = new Subject("Math");
        Subject sub2 = new Subject("Deutsch");
        Subject sub3 = new Subject("English");
        Subject sub4 = new Subject("History");
        Subject sub5 = new Subject("Science");
        Subject sub6 = new Subject("Art");

        ArrayList<Subject> subjectsA = new ArrayList<>();
        ArrayList<Subject> subjectsB = new ArrayList<>();
        subjectsA.add(sub1);
        subjectsA.add(sub2);
        subjectsA.add(sub3);

        subjectsB.add(sub4);
        subjectsB.add(sub5);
        subjectsB.add(sub6);

        ClassRoom classroomA = new ClassRoom(1, "A",subjectsA);
        ClassRoom classroomB = new ClassRoom(2, "B",subjectsB);

        Student studentSulaiman = new Student(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "emailSulaiman" ,classroomA);

        Student studentAli = new Student(2, "Ali", "Alghnam", "ali", "1234", "emailAli", classroomA);
        Student studentAliB = new Student(5, "Mohammed", "Alghnam", "mohammed", "1234", "emailMohammed", classroomB);

        Teacher teacherAhmed = new Teacher(3, "Ahmed", "Alghnam", "ahmed", "1234", "emailAhmed", sub3,classroomA);
        Teacher teacherSulaimanB = new Teacher(4, "Khaled", "Alghnam", "khaled", "1234", "emailKhaled", sub4, classroomB);

        teacherAhmed.addSubject(sub1);
        teacherAhmed.addSubject(sub2);

        teacherSulaimanB.addSubject(sub5);
        teacherSulaimanB.addSubject(sub6);

        Grade grade1 = new Grade(studentSulaiman, sub1, 90);
        Grade grade2 = new Grade(studentAli, sub2, 80);
        Grade grade3 = new Grade(studentAliB, sub3, 70);
        Grade grade3_1 = new Grade(studentAli, sub3, 70);
        Grade grade4 = new Grade(studentSulaiman, sub4, 60);

        teacherAhmed.addSubGrade(sub1, studentSulaiman, 90);
        teacherAhmed.addSubGrade(sub2, studentAli, 80);
        teacherAhmed.addSubGrade(sub3, studentAliB, 70);

//        System.out.println("All Classrooms:\n" + Arrays.asList(classroomA, classroomB));
//        System.out.println(studentSulaiman.toString());
//        System.out.println(teacherSulaiman.toString());
//        System.out.println(studentSulaimanB.toString());
//        System.out.println(teacherSulaimanB.toString());
    }
}