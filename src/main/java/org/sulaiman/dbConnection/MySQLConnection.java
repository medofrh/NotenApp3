package org.sulaiman.dbConnection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
public class MySQLConnection {
    private final Dotenv dotenv = Dotenv.load();
    private final String username = dotenv.get("DB_USER");
    private final String password = dotenv.get("DB_PASS");
    private final String host = dotenv.get("DB_HOST");
    private final String database = dotenv.get("DB_NAME");
    private final int port = Integer.parseInt(dotenv.get("DB_PORT"));

    public Connection getConnection() {
        try {
            String mysqlUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database +
                    "?useSSL=false&serverTimezone=UTC";
            return DriverManager.getConnection(mysqlUrl, this.username, this.password);
        }catch (SQLException e){
            return null;
        }
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null;
        } catch (SQLException e) {
            return false;
        }
    }
}
