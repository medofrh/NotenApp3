package org.sulaiman;

import org.sulaiman.dbConnection.MySQLConnection;
import org.sulaiman.dbConnection.SQLiteConnection;

import java.sql.*;

public class DatabaseManager {
    private static Connection connection;
    private static boolean isOnline = false;

    public DatabaseManager() {
        if (MySQLConnection.testConnection()) {
            connection = MySQLConnection.getConnection();
            System.out.println("Connected to MySQL database.");
            isOnline = true;
            syncTables();
        }else {
            if(!SQLiteConnection.databaseExists()){
                System.out.println("Error: SQLite database does not exist.");
                System.out.println("Error: Please check your internet connection and try again.");
                return;
            }
            connection = SQLiteConnection.getConnection();
            System.out.println("Connected to SQLite database.");
            isOnline = false;
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

    public boolean isOnline() {
        return isOnline;
    }

    // get the connection
    public Connection getConnection() {
        return connection;
    }

    // close the connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void syncTables() {
        if (!(MySQLConnection.testConnection() && SQLiteConnection.testConnection())) {
            System.out.println("Error: Your internet connection is not stable.");
            System.out.println("Error: Please check your internet connection and try again.");
            return;
        }

        // Remove the SQLite database file
        SQLiteConnection.removeDatabase();

        try (Connection mysqlConn = MySQLConnection.getConnection();
             Connection sqliteConn = SQLiteConnection.getConnection()) {

            // Get database name from dotenv
            String databaseName = MySQLConnection.getDatabaseName();

            // Get all tables from the specific database in MySQL
            DatabaseMetaData mysqlMetaData = mysqlConn.getMetaData();
            ResultSet tables = mysqlMetaData.getTables(databaseName, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");

                // Get table creation SQL
                String createTableSQL = getCreateTableSQL(mysqlConn, databaseName, tableName);
                try (Statement sqliteStmt = sqliteConn.createStatement()) {
                    sqliteStmt.execute(createTableSQL); // Create table in SQLite
                }

                // Transfer data
                transferData(mysqlConn, sqliteConn, tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getCreateTableSQL(Connection mysqlConn, String databaseName, String tableName) throws SQLException {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE ");
        createTableSQL.append(tableName).append(" (");

        DatabaseMetaData metaData = mysqlConn.getMetaData();
        ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);

        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String dataType = columns.getString("TYPE_NAME");
            int columnSize = columns.getInt("COLUMN_SIZE");
            boolean isNullable = "YES".equalsIgnoreCase(columns.getString("IS_NULLABLE"));

            createTableSQL.append(columnName)
                    .append(" ")
                    .append(convertSQLType(dataType, columnSize))
                    .append(isNullable ? "" : " NOT NULL")
                    .append(", ");
        }

        createTableSQL.delete(createTableSQL.length() - 2, createTableSQL.length()); // Remove last comma
        createTableSQL.append(");");

        return createTableSQL.toString();
    }

    private String convertSQLType(String mysqlType, int size) {
        switch (mysqlType.toUpperCase()) {
            case "VARCHAR":
            case "CHAR":
                return "TEXT";
            case "INT":
            case "INTEGER":
            case "SMALLINT":
            case "TINYINT":
                return "INTEGER";
            case "DECIMAL":
            case "NUMERIC":
            case "FLOAT":
            case "DOUBLE":
                return "REAL";
            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "TEXT"; // SQLite stores dates as TEXT
            case "BLOB":
                return "BLOB";
            default:
                return "TEXT"; // Default type mapping
        }
    }

    private void transferData(Connection mysqlConn, Connection sqliteConn, String tableName) throws SQLException {
        try (Statement mysqlStmt = mysqlConn.createStatement();
             ResultSet resultSet = mysqlStmt.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder insertSQL = new StringBuilder("INSERT INTO ").append(tableName).append(" VALUES (");
            for (int i = 0; i < columnCount; i++) {
                insertSQL.append("?,");
            }
            insertSQL.deleteCharAt(insertSQL.length() - 1); // Remove last comma
            insertSQL.append(")");

            try (PreparedStatement sqliteStmt = sqliteConn.prepareStatement(insertSQL.toString())) {
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        sqliteStmt.setObject(i, resultSet.getObject(i));
                    }
                    sqliteStmt.executeUpdate();
                }
            }
        }
    }
}
