package org.sulaiman;

import org.sulaiman.dbConnection.SQLiteConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//        new App();
        DatabaseManager dbManager = new DatabaseManager();
        ConsoleUI.start();
    }
}