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

        Student studentSulaiman = new Student(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "emailSulaiman"
                ,false,classroomA);

        Student studentAli = new Student(2, "Ali", "Alghnam", "ali", "1234", "emailAli", false, classroomA);
        Student studentAhmed = new Student(3, "Ahmed", "Alghnam", "ahmed", "1234", "emailAhmed", false, classroomA);

        Teacher teacherSulaiman = new Teacher(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "emailSulaiman",true, sub1,classroomA);
        Teacher teacherAli = new Teacher(2, "Ali", "Alghnam", "ali", "1234", "emailAli", true, sub2,classroomA);
        Teacher teacherAhmed = new Teacher(3, "Ahmed", "Alghnam", "ahmed", "1234", "emailAhmed", true, sub3,classroomA);

        Student studentSulaimanB = new Student(4, "Khaled", "Alghnam", "khaled", "1234", "emailKhaled", false, classroomB);
        Student studentAliB = new Student(5, "Mohammed", "Alghnam", "mohammed", "1234", "emailMohammed", false, classroomB);
        Student studentAhmedB = new Student(6, "Abdullah", "Alghnam", "abdullah", "1234", "emailAbdullah", false, classroomB);

        Teacher teacherSulaimanB = new Teacher(4, "Khaled", "Alghnam", "khaled", "1234", "emailKhaled", true, sub4, classroomB);
        Teacher teacherAliB = new Teacher(5, "Mohammed", "Alghnam", "mohammed", "1234", "emailMohammed", true, sub5, classroomB);
        Teacher teacherAhmedB = new Teacher(6, "Abdullah", "Alghnam", "abdullah", "1234", "emailAbdullah", true, sub6, classroomB);

        System.out.println("All Classrooms:\n" + Arrays.asList(classroomA, classroomB));
        System.out.println(studentSulaiman.toString());
        System.out.println(teacherSulaiman.toString());
        System.out.println(studentSulaimanB.toString());
        System.out.println(teacherSulaimanB.toString());
        System.exit(0);


        Teacher user1 = new Teacher(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "asd:"
                ,true, sub1,classroomA);

        Student user2 = new Student(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "asd:"
                ,false,classroomA);

        Grade grade1 = new Grade(1, user2, sub1, 5);
        user1.addSubGrade(sub1, user2,grade1);


        System.out.println(user2.getSubject(user2.getClassRoom()));
        // get all subjects from classRoom for student and convert to string




    }
}