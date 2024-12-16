package org.sulaiman;

import org.sulaiman.dbConnection.SQLiteConnection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        new App();
        DatabaseManager dbManager = new DatabaseManager();
        Object user = ConsoleUI.start();
        if (user != null) {
            System.out.println("Welcome " + user);
        }
    }
}