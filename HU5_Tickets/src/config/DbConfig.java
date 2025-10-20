package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/soporte_tickets";
    private static final String USER = "Andresl";
    private static final String PASSWORD = "idQ6kwx+";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
