package Config;
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
        try (InputStream input = ConfigDB.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null){
                throw new RuntimeException("No se encontro el archivo config.properties");
            }
        }
    }

    public static Connection getConnection() throws SQLException{
       // return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
