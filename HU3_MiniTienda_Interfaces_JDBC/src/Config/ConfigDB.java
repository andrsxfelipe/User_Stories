package Config;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigDB {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = ConfigDB.class.getClassLoader().getResourceAsStream("resources/config.properties")){
            if (input == null){
                throw new RuntimeException("No se encontro el archivo config.properties");
            }

            props.load(input);

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (IOException e){
            throw new RuntimeException("Error cargando configuración de BD", e);
        }
    }

    public static Connection getConnection() throws SQLException{
       return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
