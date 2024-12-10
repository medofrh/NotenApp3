package org.sulaiman.dbConnection;

import java.sql.*;
public class SQLiteConnection {
    public static Connection getConnection() {
        try {
            // save sqlite database in resources in database folder
            String sqliteUrl = "jdbc:sqlite:src/main/resources/database/local.db";
            return DriverManager.getConnection(sqliteUrl);
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null;
        } catch (SQLException e) {
            return false;
        }
    }
}
