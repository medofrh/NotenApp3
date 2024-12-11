package org.sulaiman;

import java.sql.Connection;
import java.util.ArrayList;

public class App {
    private final DatabaseList<Subject> subjects;
    private final DatabaseList<AbstractUser> users;
    private final DatabaseList<ClassRoom> classRooms;
    private final DatabaseList<Grade> grades;
    private final DatabaseManager dbManager;
    private Connection connection;
    public App() {
        this.subjects = new DatabaseList<>();
        this.users = new DatabaseList<>();
        this.classRooms = new DatabaseList<>();
        this.grades = new DatabaseList<>();
        this.dbManager = new DatabaseManager();

        // erzeuge Testdaten (hier aus Datenbank einlesen!)
        this.processData();

        // Testdurchlauf
        AbstractUser currentUser = this.users.getByUid(3); // 3: Lehrer!
        this.addGrade(currentUser, this.subjects.getByUid(1), (Student)this.users.getByUid(1), 1.3);
        this.addStudentToSubject(currentUser, (Student)this.users.getByUid(2), this.subjects.getByUid(3));
        this.removeStudentFromSubject(currentUser, (Student)this.users.getByUid(1), this.subjects.getByUid(1));
        ArrayList<Grade> mohamadsGrades = ((Student) this.users.getByUid(1)).getGrades();

    }

    public void processData() {
        dbManager.syncTables();
        try {
            dbManager.select("SELECT * FROM users");
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // lege Klassenraum an
        {
            // schleife mit daten von Klassenräumen hier anlegen
            this.classRooms.add(
                    new ClassRoom(1, "A")
            );
        }

        // erzeuge Schüler
        {
            this.users.add(
                new Student(1, "Sulaiman", "Alghnam", "sulaiman", "1234", "emailSulaiman")
            );
            this.users.add(
                    new Student(2, "Yannik", "Börgener", "yannik", "1234", "emailYannik")
            );
        }

        // erzeuge Lehrer
        {
            this.users.add(
                    new Teacher(3, "Ahmed", "Alghnam", "ahmed", "1234", "emailAhmed")
            );
        }



        // lade Fächer
        String[] subjectNames = {
                "Math",
                "Deutsch",
                "English",
                "History",
                "Science",
                "Art"
        };
        for(int i = 0; i < subjectNames.length; i++){
            ClassRoom classRoom = this.classRooms.getByUid(1);
            // checke ob user mit uid 3 wirklich lehrer ist - und nicht schüler
            Teacher teacher = (Teacher)this.users.getByUid(3);
            if(teacher.isTeacher()) {
                this.subjects.add(
                        new Subject(i, subjectNames[i], classRoom, teacher)
                );
            }
        }

        // füge Schüler zu fächern hinzu
        {
            // checke ob user mit uid 1 wirklich schüler ist - und nicht lehrer
            Student mohamad = (Student)this.users.getByUid(1);
            if(!mohamad.isTeacher()) {
                mohamad.addSubject(
                        this.subjects.getByUid(1)
                );
                mohamad.addSubject(
                        this.subjects.getByUid(2)
                );
                mohamad.addSubject(
                        this.subjects.getByUid(3)
                );
            }

            Student yannik = (Student)this.users.getByUid(2);
            if(!yannik.isTeacher()) {
                yannik.addSubject(
                        this.subjects.getByUid(1)
                );
                yannik.addSubject(
                        this.subjects.getByUid(2)
                );
            }
        }
    }

    public void addGrade(AbstractUser currentUser, Subject subject, Student student, double gradeDouble) {
        if(!currentUser.isTeacher()) return;

        Grade grade = new Grade(0, student, subject, gradeDouble);
        subject.addGrade(grade);
    }

    public void addStudentToSubject(AbstractUser abstractUser, Student student, Subject subject) {
        if(!abstractUser.isTeacher()) return;
        student.addSubject(subject);
    }

    public void removeStudentFromSubject(AbstractUser abstractUser, Student student, Subject subject) {
        if(!abstractUser.isTeacher()) return;
        student.removeSubject(subject);
    }

    public AbstractUser getStudent(AbstractUser currentUser, AbstractUser user) {
        if(
            currentUser.isTeacher() ||
            currentUser == user
        ) return user;

        return null;
    }
}
