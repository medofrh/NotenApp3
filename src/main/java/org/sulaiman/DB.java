package org.sulaiman;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.io.File;

public class DB {
    private Connection connection;
    private final String username;
    private final String password;
    private final String host;
    private final String database;
    private final int port;
    private final String sqliteDbPath = "local.db";

    public DB() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASS");
        this.host = dotenv.get("DB_HOST");
        this.database = dotenv.get("DB_NAME");
        this.port = Integer.parseInt(dotenv.get("DB_PORT"));
    }

    public Connection connect() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                // try to connect to MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                String mysqlUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database +
                        "?useSSL=false&serverTimezone=UTC";
                this.connection = DriverManager.getConnection(mysqlUrl, this.username, this.password);
                System.out.println("Connected to MySQL");
            }catch (ClassNotFoundException | SQLException e){
                // Fall back to SQLite if MySQL is not available
                System.out.println("MySQL not available, falling back to SQLite");
                this.connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteDbPath);
            }
        }
        return this.connection;
    }

    public void closeConnection () throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
            System.out.println("Database connection closed.");
        }
    }

    public ResultSet select(String sql) throws SQLException {
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public void syncDatabases() throws SQLException {
        //check if connection is established and it's SQLite
        if(this.connection.getMetaData().getURL().contains("sqlite")) {
            try(Statement stmt = this.connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM grades");
                while (rs.next()) {
                    int gradeId = rs.getInt("grade_id");
                    int studentId = rs.getInt("student_id");
                    int grade = rs.getInt("grade");

                    // Insert the data into MySQL
                    insertIntoMySQL(gradeId, studentId, grade);
                }
            }
        }
    }

    public void syncTableToMySQL(String tableName, String[] columns) throws SQLException {
        // check if connection is established and it's SQLite
        if (!this.connection.getMetaData().getURL().contains("sqlite")) {
            return;
        }
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                String sql = "INSERT INTO " + tableName + " (";
                for (String column : columns) {
                    sql += column + ", ";
                }
                sql = sql.substring(0, sql.length() - 2) + ") VALUES (";
                for (String column : columns) {
                    sql += rs.getString(column) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2) + ")";
                try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }

    private void insertIntoMySQL(int gradeId, int studentId, int grade) throws SQLException {
        String sql = "INSERT INTO grades (grade_id, student_id, grade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setInt(1, gradeId);
            pstmt.setInt(2, studentId);
            pstmt.setInt(3, grade);
            pstmt.executeUpdate();
        }
    }
}
