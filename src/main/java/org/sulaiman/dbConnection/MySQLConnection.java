package org.sulaiman.dbConnection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
public class MySQLConnection {
    private final static Dotenv dotenv = Dotenv.load();
    private final static String username = dotenv.get("DB_USER");
    private final static String password = dotenv.get("DB_PASS");
    private final static String host = dotenv.get("DB_HOST");
    private final static String database = dotenv.get("DB_NAME");
    private final static int port = Integer.parseInt(dotenv.get("DB_PORT"));

    public static Connection getConnection() {
        try {
            String mysqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + database +
                    "?useSSL=false&serverTimezone=UTC";
            return DriverManager.getConnection(mysqlUrl, username, password);
        }catch (SQLException e){
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
