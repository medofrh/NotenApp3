package org.sulaiman;

import org.sulaiman.dbConnection.SQLiteConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        new App();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        AbstractUser user = ConsoleUI.start();
        if (user != null) {
            ArrayList<Subject> subjects = ConsoleUI.getSubjects(user);

            for (Subject subject : subjects) {
                System.out.println(subject);
                System.out.println(subject.getStudents());
            }
        }
    }
}