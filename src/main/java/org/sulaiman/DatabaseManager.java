package org.sulaiman;

import org.sulaiman.dbConnection.MySQLConnection;
import org.sulaiman.dbConnection.SQLiteConnection;

import java.sql.*;

public class DatabaseManager {
    private static Connection connection;

    public DatabaseManager() {
        if (MySQLConnection.testConnection()) {
            connection = MySQLConnection.getConnection();
        }else {
            connection = SQLiteConnection.getConnection();
        }
    }

    // select method for MySQL and SQLite databases
    public ResultSet select(String sql) throws SQLException {
        //filter the sql query to determine if it's a select query
        if (!sql.trim().toLowerCase().startsWith("select")) {
            throw new SQLException("Invalid query, only SELECT queries are allowed.");
        }
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }
}
