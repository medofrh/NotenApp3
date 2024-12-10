package org.sulaiman.dbConnection;

import java.sql.*;
public class SQLiteConnection {
    static final String sqliteUrl = "jdbc:sqlite:local.db";
    public static Connection getConnection() {
        try {
            // save sqlite database in resources in database folder
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
