package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://168.119.183.3:3307/antonymruiz";
    private static final String USER = "root";
    private static final String PASSWORD = "g0tIFJEQsKHm5$34Pxu1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
